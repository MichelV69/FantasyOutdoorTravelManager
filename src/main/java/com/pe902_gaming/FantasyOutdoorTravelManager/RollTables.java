package com.pe902_gaming.FantasyOutdoorTravelManager;

import java.util.ArrayList;
import java.util.List;

public class RollTables
{
  public String getResultFromTableRoll(int IncomingDiceRoll, String NameOfTable)
  {
    List<String[]> TableDataSet = getColumnsFor(NameOfTable);
    System.out.println(">> DEBUG TableDataSet.size ["+TableDataSet.size()+"]");


    String TextResultFromTableRoll = "";
    for (String[] CurrentTableRow : TableDataSet )
    {
      // break row to useful structure
      int TableRollLowEdge  = Integer.parseInt( CurrentTableRow[0] ) ;
      int TableRollHighEdge = Integer.parseInt( CurrentTableRow[1] ) ;

      // check if roll fits && if so assign results
      System.out.println(">> DEBUG CurrentTableRow2 ["+CurrentTableRow[2]+"]");

      if ((TableRollLowEdge <= IncomingDiceRoll) && (IncomingDiceRoll <= TableRollHighEdge))
        TextResultFromTableRoll = CurrentTableRow[2];

    } // end for CSVTableRows

    System.out.println(">> DEBUG IncomingDiceRoll ["+IncomingDiceRoll+"]");
    System.out.println(">> DEBUG TextResultFromTableRoll ["+TextResultFromTableRoll+"]");
    return TextResultFromTableRoll;
  } // end getResultFromTableRoll

  public List<String[]> getColumnsFor(String NameOfTable)
  {
    String[] RowData = new String[3];
    ArrayList<String[]> ColumnData = new ArrayList<String[]>();

    switch (NameOfTable.toLowerCase())
    {
      case  "encounter_weather_spring":
        ColumnData.add(new String[]{"1", "6", "Cold snap (around 0 degrees Celsius). Every creature without appropriate clothing, shelter or a source of heat must make a DC 10 Constitution save or suffer a level of exhaustion per 2 hours. Creatures with resistance or immunity to cold damage suffer no affects. A cold snap lasts 48 hours."});
        ColumnData.add(new String[]{"7", "10", "Rain. Every square or kilometre of movement costs an additional square or kilometre of movement. Disadvantage on Constitution checks caused by cold weather. Rain lasts until the next overland travel roll."});
        ColumnData.add(new String[]{"11", "11", "Heavy Rain. Every square or kilometre of movement costs 2 additional squares or kilometres of movement. Disadvantage on range attacks, perception checks. Disadvantage on Constitution checks caused by cold weather. Visibility (for torches, dark vision, etc.) is halved. Heavy rain lasts until the next overland travel roll."});
        ColumnData.add(new String[]{"12", "12", "Torrential Downpour. Every square or kilometre of movement costs 3 additional squares or kilometres of movement. Disadvantage on Constitution checks caused by cold weather. Visibility (for torches, dark vision, etc.) is reduced to one quarter. Torrential downpour lasts until the next overland travel."});
        break;

      case  "encounter_weather_summer":
        ColumnData.add(new String[]{"1", "6", "Heat wave (around 40+ degrees Celsius). Every creature without appropriate clothing, shelter or a source of cooling must make a DC 10 Constitution save or suffer a level of exhaustion per 2 hours. Creatures with resistance or immunity to heat damage suffer no affects. A heat wave lasts 48 hours."});
        ColumnData.add(new String[]{"7", "10", "Rain. Every square or kilometre of movement costs an additional square or kilometre of movement. Disadvantage on Constitution checks caused by cold weather. Rain lasts until the next overland travel roll."});
        ColumnData.add(new String[]{"11", "11", "Heavy Rain. Every square or kilometre of movement costs 2 additional squares or kilometres of movement. Disadvantage on range attacks, perception checks. Disadvantage on Constitution checks caused by cold weather. Visibility (for torches, dark vision, etc.) is halved. Heavy rain lasts until the next overland travel roll."});
        ColumnData.add(new String[]{"12", "12", "Severe Storm. Thunder, lightning, hail, high-winds, rain.  Every square or kilometre of movement costs 1 additional squares or kilometres of movement.  Visibility (for torches, dark vision, etc.) is halved.The group must make a 4 success Group Skill Test (DC14), with a Risk Effect of 1d4+1 damage +1 Level of Exhaustion (hypothermia, bludgeoning from hail, thunder from near-miss, etc.).  Severe Storm lasts 1 hour, and preceded and followed by the normal seasonal conditions."});
        ColumnData.add(RowData);
        break;

      case  "encounter_weather_autumn":
        ColumnData.add(new String[]{"1", "6", "Cold snap (around 0 degrees Celsius). Every creature without appropriate clothing, shelter or a source of heat must make a DC 10 Constitution save or suffer a level of exhaustion per 2 hours. Creatures with resistance or immunity to cold damage suffer no affects. A cold snap lasts 48 hours."});
        ColumnData.add(new String[]{"7", "10", "Rain. Every square or kilometre of movement costs an additional square or kilometre of movement. Disadvantage on Constitution checks caused by cold weather. Rain lasts until the next overland travel roll."});
        ColumnData.add(new String[]{"11", "11", "Heavy Rain. Every square or kilometre of movement costs 2 additional squares or kilometres of movement. Disadvantage on range attacks, perception checks. Disadvantage on Constitution checks caused by cold weather. Visibility (for torches, dark vision, etc.) is halved. Heavy rain lasts until the next overland travel roll."});
        ColumnData.add(new String[]{"12", "12", "Wild fire. A substantial area of nearby forest or grasslands is engulfed in an out-of-control blaze.  Thick, heavy smoke is in the air and wild animals are fleeing the flames in panic.  Every square or kilometre of movement costs an additional square or kilometre of movement.  The group must make a 4 success Group Skill Test (DC14), with a Risk Effect of 1d4+1 damage +1 Level of Exhaustion (smoke, embers, collision with fleeing animals, etc).  The effects of the Wild Fire last until the next overland travel roll."});
        break;

      case  "encounter_weather_winter":
        ColumnData.add(new String[]{"1", "6", "Deep snow. Roads, lanes and fields are blanketed in 60+ cm of snow after a recent storm.  Every square or kilometre of movement costs an additional square or kilometre of movement.  Disadvantage on Constitution checks caused by cold weather.  Advantage on Survival rolls for tracking.  Deep snow conditions last until the next overland travel roll."});
        ColumnData.add(new String[]{"7", "10", "Snow. Every square or kilometre of movement costs 2 additional squares or kilometres of movement. Disadvantage on Constitution checks caused by cold weather. Snow lasts until the next overland travel roll."});
        ColumnData.add(new String[]{"11", "11", "Heavy Snow. Every square or kilometre of movement costs 3 additional squares or kilometres of movement. Disadvantage on range attacks, perception checks. Disadvantage on Constitution checks caused by cold weather. Visibility (for torches, dark vision, etc.) is halved. Heavy Snow lasts until the next overland travel roll."});
        ColumnData.add(new String[]{"12", "12", "Blizzard. Every square or kilometre of movement costs 4 additional squares or kilometres of movement. Disadvantage on Constitution checks caused by cold weather. Disadvantage on Perception checks cause by high winds and blowing snow.  Visibility (for torches, dark vision, etc.) is reduced to one quarter. The group must make a 4 success Group Skill Test (DC14), with a Risk Effect of 1d4+1 damage +1 Level of Exhaustion (hypothermia, wind-lash, blown objects, etc). Blizzard lasts until the next overland travel."});
        break;

      default:
        throw new IllegalStateException("Unexpected value: " + NameOfTable.toLowerCase());
    } // end switch

    System.out.println(">> DEBUG ColumnData ["+ColumnData+"]");
    return ColumnData;
  } // end getColumnsFor
} // end class RollTables
