package com.flowingcode.vaadin.addons.datetimerangepicker;

import com.flowingcode.vaadin.addons.datetimerangepicker.ui.DateTimeRangePickerI18n;
import com.flowingcode.vaadin.addons.demo.DemoSource;
import com.flowingcode.vaadin.addons.datetimerangepicker.ui.DateTimeRangePicker;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

@PageTitle("States")
@SuppressWarnings("serial")
@Route(value = "dtrp/states", layout = DateTimeRangePickerTabbedView.class)
@DemoSource
public class StatesDemo extends VerticalLayout {

  private boolean indicator = true;
  private final Map<PickerSection, Boolean> readOnly = new HashMap<>();
  private final Map<PickerSection, Boolean> visible = new HashMap<>();

  public StatesDemo() {
    setSizeFull();
    addClassNames(AlignItems.CENTER);

    // Component creation
    DateTimeRangePicker addon = new DateTimeRangePicker();
    // Set the first or leftmost day
    addon.setFirstWeekDay(DayOfWeek.THURSDAY);
    // Use i18n utility class
    addon.setI18n(new DateTimeRangePickerI18n()
        .setDatesTitle("Custom date title")
        .setTimeChipsText("AM", "PM", "AM + PM")
        .setTimesPlaceholder("Begin", "End")
    );

    VerticalLayout buttonLayout = new VerticalLayout();
    HorizontalLayout readOnlyLayout = new HorizontalLayout();
    HorizontalLayout visibleLayout = new HorizontalLayout();

    Button indicatorButton = new Button("Toggle indicator", ev ->
    {
      indicator = !indicator;
      addon.setIndicatorVisible(indicator);
    });

    for(PickerSection section : PickerSection.values()) {
      readOnlyLayout.add(
          new Button("Toggle " + section.name().toLowerCase() + " read only",
              ev -> {
                boolean value = !readOnly.getOrDefault(section, false);
                readOnly.put(section, value);
                switch(section) {
                  case DATES -> addon.setDatesReadOnly(value);
                  case DAYS -> addon.setDaysReadOnly(value);
                  case TIMES -> addon.setTimesReadOnly(value);
                }
              }
          )
      );
      visibleLayout.add(
          new Button("Toggle " + section.name().toLowerCase() + " visible",
              ev -> {
                boolean value = !visible.getOrDefault(section, false);
                visible.put(section, value);
                switch(section) {
                  case DATES -> addon.setDatesVisible(value);
                  case DAYS -> addon.setDaysVisible(value);
                  case TIMES -> addon.setTimesVisible(value);
                }
              }
          )
      );
    }

    buttonLayout.setAlignItems(Alignment.CENTER);
    readOnlyLayout.setAlignItems(Alignment.CENTER);
    visibleLayout.setAlignItems(Alignment.CENTER);

    buttonLayout.add(indicatorButton, readOnlyLayout, visibleLayout);

    add(addon, buttonLayout);

  }

  private enum PickerSection {DATES, DAYS, TIMES};

}
