package com.sanfernando.sanfernando.utils;

import java.sql.Time;
import java.text.DecimalFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeUtils {
  private Time time;
  private static final DecimalFormat decimalFormat = new DecimalFormat("#.00");

  public double convertTimeToHours(Time time) {
    try {
      int hours = time.toLocalTime().getHour();
      int minutes = time.toLocalTime().getMinute();
      double response = hours + (minutes / 60.0);
      return Double.parseDouble(decimalFormat.format(response));
    }catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }
}
