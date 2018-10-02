package com.danhammant.jobtracker.view;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;

public class NonExistantJobDialog {
    Dialog dialog = new Dialog();

    public NonExistantJobDialog() {

        dialog.add(new Label("WARNING: Job does not exist. Can't delete"));

        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(true);
    }

    public void open() {
        dialog.open();
    }
}
