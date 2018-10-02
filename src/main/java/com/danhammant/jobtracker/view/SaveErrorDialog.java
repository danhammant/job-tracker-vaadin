package com.danhammant.jobtracker.view;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;

public class SaveErrorDialog {
    Dialog dialog = new Dialog();

    public SaveErrorDialog() {

        dialog.add(new Label("WARNING: Either the job is a duplicate, or one or more fields are empty."));

        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(true);
    }

    public void open() {
        dialog.open();
    }
}
