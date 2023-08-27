package com.dat.controls;

import com.dat.models.District;
import com.dat.models.Province;
import com.dat.models.Ward;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public Button buttonLoadProvinces;
    public Button buttonLoadDistricts;
    public Button buttonLoadWards;
    public TableView tableProvinces;
    public TableView tableDistricts;
    public TableView tableWards;
    public TextField tfSearch;
    public Label lbInfo;
    public ComboBox<String> chooseSearch;
    private final ProvinceController pC = new ProvinceController();
    private final DistrictController dC = new DistrictController();
    private final WardController wC = new WardController();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toTableProvinces(pC.getList());
        toTableDistricts(dC.getList());
        toTableWards(wC.getList());
        String[] week_days = {"None relative search", "Relative search"};
        chooseSearch.setItems(FXCollections.observableArrayList(week_days));
        chooseSearch.getSelectionModel().select(0);
    }

    private void toTableDistricts(HashMap<Integer, District> list) {
        ObservableList<Map.Entry<Integer, District>> dataView = FXCollections.observableArrayList(list.entrySet());
        TableColumn<Map.Entry<Integer, District>, String> id = new TableColumn<>("District");
        id.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey() + ""));
        id.setVisible(false);
        tableDistricts.getColumns().setAll(id);
        TableColumn<Map.Entry<Integer, District>, String> district = new TableColumn<>("District");
        district.setCellValueFactory(p -> new SimpleStringProperty(list.get(p.getValue().getKey()).getName()));
        tableDistricts.getColumns().add(district);
        tableDistricts.setItems(dataView);
    }

    private void toTableWards(HashMap<Integer, Ward> list) {
        var dataView = FXCollections.observableArrayList(list.entrySet());
        TableColumn<Map.Entry<Integer, Ward>, String> wards = new TableColumn<>("Wards");
        wards.setCellValueFactory(p -> new SimpleStringProperty(list.get(p.getValue().getKey()).getName()));
        tableWards.getColumns().setAll(wards);
        tableWards.setItems(dataView);
    }

    private void toTableProvinces(HashMap<String, Province> list) {
        ObservableList<Map.Entry<String, Province>> dataView = FXCollections.observableArrayList(list.entrySet());
        TableColumn<Map.Entry<String, Province>, String> province = new TableColumn<>("Province");
        province.setCellValueFactory(p -> new SimpleStringProperty(list.get(p.getValue().getKey()).getName()));
        tableProvinces.getColumns().setAll(province);
        tableProvinces.setItems(dataView);
    }

    public void onClickTableProvinces() {
        var item=(Map.Entry<String, Province>) tableProvinces.getSelectionModel().getSelectedItem();
        if(item==null)return;
        var province =item.getKey();
        var listDistricts = dC.listDistrict(province);
        var listWards = wC.getList(dC.listDistrict(province));
        toTableDistricts(listDistricts);
        toTableWards(listWards);
        String info = province + " có " + listDistricts.size() + " quận/huyện và " + listWards.size() + " xã/phường";
        lbInfo.setText(info);

    }

    public void onClickTableDistricts() {
        var item=(Map.Entry<Integer, District>) tableDistricts.getSelectionModel().getSelectedItem();
        if(item==null)return;
        var districtID = item.getKey();
        var listWards = wC.getList(districtID);
        toTableWards(listWards);
        String province = dC.getList().get(districtID).getProvince();
        String district = dC.getList().get(districtID).getName();
        String info = province + ", " + district + " có " + listWards.size() + " xã/phường";
        lbInfo.setText(info);
    }

    public void onClickTableWards() {
        var item = (Map.Entry<Integer, Ward>) tableWards.getSelectionModel().getSelectedItem();
        if(item==null)return;
        var wardID = item.getKey();
        var ward = item.getValue();
        var district = dC.getList().get(wC.getList().get(wardID).getDistrictID());
        String districtName = district.getName();
        String province = district.getProvince();
        String info = province + ", " + districtName + ", " + ward.getName();
        lbInfo.setText(info);
    }

    public void onClickLoadAllProvinces() {
        toTableProvinces(pC.getList());
        lbInfo.setText("Có tất cả "+pC.getList().size()+" tỉnh thành");
    }

    public void onClickLoadAllWards() {
        toTableWards(wC.getList());
        lbInfo.setText("Có tất cả "+wC.getList().size()+" xã/phường");
    }

    public void onClickLoadAllDistricts() {
        toTableDistricts(dC.getList());
        lbInfo.setText("Có tất cả "+dC.getList().size()+" quận/huyện");
    }

    public void clear() {
        tfSearch.setText("");
    }

    public void searchAll() {
        String info = tfSearch.getText();
        if (chooseSearch.getSelectionModel().getSelectedIndex() == 0) {
            toTableProvinces(pC.search(info));
            toTableDistricts(dC.searchList(info));
            toTableWards((wC.searchList(info)));
        }
        else{
            toTableProvinces(pC.searchRelative(info));
            toTableDistricts(dC.searchRelative(info));
            toTableWards((wC.searchRelative(info)));
        }
    }
}
