package com.danhammant.jobtracker.view;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class JobSiteLinks extends HorizontalLayout {

    public JobSiteLinks() {
        Anchor indeedLink = new Anchor("https://www.indeed.nl", "Indeed NL");
        indeedLink.setTarget("_blank");

        Anchor linkedInLink = new Anchor("https://www.linkedin.com/jobs/", "LinkedIn Jobs");
        linkedInLink.setTarget("_blank");

        Anchor glassDoorLink = new Anchor("https://www.glassdoor.co.uk/index.htm", "Glassdoor");
        glassDoorLink.setTarget("_blank");

        Anchor monsterLink = new Anchor("https://www.monsterboard.nl/", "Monster NL");
        monsterLink.setTarget("_blank");

        Anchor darwinLink = new Anchor("https://www.darwinrecruitment.com/#", "Darwin Recruitment");
        darwinLink.setTarget("_blank");

        add(indeedLink, linkedInLink, glassDoorLink, monsterLink, darwinLink);

    }
}
