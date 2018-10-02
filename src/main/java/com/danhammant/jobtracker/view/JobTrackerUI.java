package com.danhammant.jobtracker.view;

import com.danhammant.jobtracker.model.Job;
import com.danhammant.jobtracker.repository.JobRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route(value = "jobs")
public class JobTrackerUI extends HorizontalLayout {
    private final String all = "All";
    private final String applied = "Applied";
    private final String notApplied = "Not Applied";

    private String limiter = "";

    private JobRepository repository;
    private JobForm form;

    private Grid<Job> grid = new Grid<>();
    private TextField filterText = new TextField();

    public JobTrackerUI(JobRepository repository) {
        this.repository = repository;
        this.form = new JobForm(this, this.repository);


        //Create left side

        Label addNewJobLbl = new Label("Click on 'Job Title' box to input new job...");

        VerticalLayout leftSide = new VerticalLayout();
        leftSide.add(addNewJobLbl, form, new JobSiteLinks());

        filterText.setPlaceholder("Filter by location...");
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList());

        //Create tools at top right
        Button clearFilterTextBtn = new Button(
                new Icon(VaadinIcon.CLOSE_CIRCLE));
        clearFilterTextBtn.addClickListener(
                 e -> filterText.clear());

        HorizontalLayout filtering  = new HorizontalLayout(filterText, clearFilterTextBtn);

        Label showRadioLabel = new Label("Show: ");
        RadioButtonGroup<String> limitShownJobs = new RadioButtonGroup<>();
        limitShownJobs.setItems(all, applied, notApplied);

        limitShownJobs.addValueChangeListener(e -> {
            limiter = e.getValue();
            updateList();
        });

        //Group tools
        HorizontalLayout limitShown = new HorizontalLayout(showRadioLabel, limitShownJobs);
        HorizontalLayout filterAndShow = new HorizontalLayout(filtering, limitShown);


        //Format grid
        grid.setSizeFull();

        grid.addColumn(Job::getTitle).setHeader("Title").setResizable(true);
        grid.addColumn(Job::getCompany).setHeader("Company").setResizable(true);
        grid.addColumn(Job::getLocation).setHeader("Location").setResizable(true);
        grid.addColumn(Job::getUrl).setHeader("URL").setResizable(true);
        grid.addColumn(Job::getStatus).setHeader("Status").setResizable(true);

        VerticalLayout rightSide = new VerticalLayout();
        rightSide.add(filterAndShow, grid);
        add(leftSide, rightSide);

        setHeight("100vh");

        updateList();



        grid.asSingleSelect().addValueChangeListener(event -> {
            form.setJob(event.getValue());
        });
    }


    public void updateList() {
        if (filterText.isEmpty() && (limiter.isEmpty() || limiter.equals(all))) {
            grid.setItems(repository.findAll());
        } else if (limiter.isEmpty() || limiter.equals(all)) {
            grid.setItems(repository.findByLocationStartsWithIgnoreCase(filterText.getValue()));
        } else {
            grid.setItems(repository.findByLocationStartsWithIgnoreCaseAndAndStatus(filterText.getValue(), limiter));
        }

    }

    public void clearSelection() {
        grid.asSingleSelect().clear();
    }








}
