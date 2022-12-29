package com.fran.CustomClasses;

import javafx.scene.control.Button;

public class TableViewElement {
    private String raceName;
    private Button finish;

    public TableViewElement(String raceName) {
        this.raceName = raceName;
        this.finish = new Button("Delete");
    }

    public Button getFinish() {
        return finish;
    }

    public String getRaceName() {
        return raceName;
    }
}
