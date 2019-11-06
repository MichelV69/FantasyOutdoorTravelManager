package com.pe902_gaming.FantasyOutdoorTravelManager;

// ---
import com.opensymphony.xwork2.ActionSupport;

import java.lang.*;
import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
// ---


@SuppressWarnings("serial")
public class GetTravelReport extends ActionSupport 
{
  // properties
  private List<String> ListOfSeasons;
  private List<String> PhasesOfSeasons;
  private List<String> EncounterRiskLevels;

  private final float CR_BY_KM_RATIO = (float) 1/42;

  private int EncounterRiskIndex = 0;
  private int SeasonListIndex = 0;
  private int SeasonPhaseIndex = 0;
  private int CivilizationDistanceKM = 0;
  private int EncounterD12Roll = 0;

  private String DefaultConditionsForSeason = "A warm and clear day";
  private String EncounterMonsterCR = "";
  private String EncounterWeatherEffects = "";

  private Boolean DoValidate = false;

  private  RandomArtwork CurrentArtwork;

  // gets & sets
  public List<String> getEncounterRiskLevels() { return EncounterRiskLevels; }
  public String getEncounterRisk() { return EncounterRiskLevels.get(EncounterRiskIndex); }
  public void setEncounterRisk(String incomingText) { EncounterRiskIndex  = EncounterRiskLevels.indexOf(incomingText); }

  public List<String> getListOfSeasons() { return ListOfSeasons; }
  public int getSeasonListIndex() { return SeasonListIndex; }
  public String LookUpSeasonNameByIndex(int incomingText) { return ListOfSeasons.get(incomingText); }
  public String getSeasonName() { return ListOfSeasons.get(SeasonListIndex); }
  public void setSeasonName(String incomingText) { SeasonListIndex  = ListOfSeasons.indexOf(incomingText); }
  public void setSeasonListIndex(int incomingValue) { SeasonListIndex = incomingValue; }

  public int getSeasonPhaseIndex() { return SeasonPhaseIndex; }
  public void getSeasonPhaseIndex(int incomingValue) { SeasonPhaseIndex = incomingValue; }
  public List<String> getPhasesOfSeasons() { return PhasesOfSeasons; }
  public String getSeasonPhaseName() { return PhasesOfSeasons.get(SeasonPhaseIndex); }
  public void setSeasonPhaseName(String incomingText) { SeasonPhaseIndex = PhasesOfSeasons.indexOf(incomingText); }
  
  public int getCivilizationDistanceKM() { return CivilizationDistanceKM; }
  public void setCivilizationDistanceKM(String incomingText) { CivilizationDistanceKM = Integer.parseInt(incomingText); }
  
  public String getDefaultConditionsForSeason() { return DefaultConditionsForSeason; }

  public String getEncounterMonsterCR() { return EncounterMonsterCR; }
  public String getEncounterWeatherEffects() { return EncounterWeatherEffects; }

  public void setSubmitPressed(String incomingText) { DoValidate = true; }

  public String CurrentArtworkLink() {System.out.println('+') ; return CurrentArtwork.getFilename();}
  public String CurrentArtworkALT() {System.out.println('+') ; return CurrentArtwork.getTextAltTag() + " by " + CurrentArtwork.getArtistName();}
  public String CurrentArtworkID() {System.out.println('+') ; return CurrentArtworkALT().replaceAll("\\s", "");}

  // constructor
  public GetTravelReport()
  {
    EncounterRiskLevels = new ArrayList<String>();
    EncounterRiskLevels.add("Normal");
    EncounterRiskLevels.add("Dangerous");
    EncounterRiskLevels.add("Savage");

    ListOfSeasons = new ArrayList<String>();
    ListOfSeasons.add("Spring");
    ListOfSeasons.add("Summer");
    ListOfSeasons.add("Autumn");
    ListOfSeasons.add("Winter");

    PhasesOfSeasons = new ArrayList<String>();
    PhasesOfSeasons.add("Early");
    PhasesOfSeasons.add("Mid");
    PhasesOfSeasons.add("Late");

    CurrentArtwork = new RandomArtwork();

    //System.out.println(">>> DEBUG: GetTravelReport.CurrentArtworkLink ["+CurrentArtworkLink()+"]");
    //System.out.println(">>> DEBUG: GetTravelReport.CurrentArtworkALT ["+CurrentArtworkALT()+"]");
    //System.out.println(">>> DEBUG: GetTravelReport.CurrentArtworkID ["+CurrentArtworkID()+"]");
  } // end GetTravelReport

  // overrides
  @Override
  public void validate()
  {
    if (DoValidate) 
    {
      if (SeasonListIndex < 0)
        addFieldError("SeasonListIndex", getText("GetTravelReport.error.pick_season"));

      if (SeasonListIndex < 0)
        addFieldError("SeasonPhaseIndex", getText("GetTravelReport.error.pick_phase"));

      if (CivilizationDistanceKM < 0)
        addFieldError("CivilizationDistanceKM", getText("GetTravelReport.error.positive_distance"));  
    }  // end if-DoValidate

  } // end-validate

  @Override
  public String execute() throws Exception 
  {
    // -=-- workflow --=- 
    // ... user-get season
    // ... user-get season-phase
    // ... user-get distance to road or settlement
    
    // ... table-get default conditions
    setDefaultConditionsForSeason();
    
    // ... roll 1d12
    EncounterD12Roll = dice(1,12);
    System.out.println(" >> DEBUG: EncounterD12Roll ["+EncounterD12Roll+"]");

    int CombatEncounterRollFloor = 10;
    int EncounterCRModifier = 0;

    switch (EncounterRiskIndex) 
    {
      case 1:
        CombatEncounterRollFloor = 9;
        EncounterCRModifier = 1;
      break;
      case 2:
        CombatEncounterRollFloor = 8;
        EncounterCRModifier = 2;
      break;  
    }

    // ...   10 - Monster Encounter, CR Based on distance
    if (CombatEncounterRollFloor <= EncounterD12Roll && EncounterD12Roll <= 11) 
    {
      int CR_Bottom = Math.max( Math.round( CivilizationDistanceKM * CR_BY_KM_RATIO ) - 2, 0 ) + EncounterCRModifier;
      int CR_Top = 2 + Math.round( CivilizationDistanceKM * CR_BY_KM_RATIO ) + EncounterCRModifier;
      EncounterMonsterCR = "CR" + CR_Bottom + " to CR" + CR_Top;
    }
    // ...   11 - Both
    // ...   12 - Weather Encounter, Based on Season
    if (11 == EncounterD12Roll || EncounterD12Roll == 12) 
    {
      EncounterWeatherEffects = getWeatherEffectsEncountersBySeason();
    }

    return ActionSupport.SUCCESS;
  }

  // other methods
  private static int random(int min, int max) { return ThreadLocalRandom.current().nextInt(min, max); } 

  private int dice(int number, int faces)
  {
    int TotalDiceRoll = 0;

    for (int loops = 0; loops < number; loops++) 
    {
      TotalDiceRoll += random(1, faces);
    }

    return TotalDiceRoll;
  } // end dice

  private void setDefaultConditionsForSeason()
  {
    final String CurrentConditionsFormatString = "{0} (around {1}C), {2} cloud cover, with wind {3}.";
    String TempCategoryText = "";
    int SeasonPhaseIndexModiferDegress = 0; // TODO:  better curve logic
    int BaseSeasonTemperatureDegrees = 0;
    int TemperatureVarianceDicePool = 0;
    int TemperatureVarianceDiceSize = 0;
    int TemperatureVarianceDiceRoll = 0;
    int TempDiceRoll = 0;
    int CloudCoverModifier = 0;
    String CloudCoverText = "";
    String WindDataText = "";
    String WorkPhrase = "";

    System.out.println("\n >> DEBUG: SeasonListIndex ["+SeasonListIndex+"]");
    System.out.println(" >> DEBUG: SeasonPhaseIndex ["+SeasonPhaseIndex+"]");

    switch (SeasonListIndex) 
    {
      case 0:
        // spring
        TempCategoryText = "Cool";
        SeasonPhaseIndexModiferDegress = SeasonPhaseIndex - 1;
        TemperatureVarianceDicePool = 2;
        TemperatureVarianceDiceSize = 8;
        BaseSeasonTemperatureDegrees = 3;  // avg 9 + 6 = 12
        CloudCoverModifier = -1;
      break;

      case 1:
        // summer
        TempCategoryText = "Warm";
        SeasonPhaseIndexModiferDegress = SeasonPhaseIndex - 1;
        TemperatureVarianceDicePool = 3;
        TemperatureVarianceDiceSize = 6;
        BaseSeasonTemperatureDegrees = 13; // avg 11 + 13 = 25
        CloudCoverModifier = -3;
      break;

      case 2:
        // autumn
        TempCategoryText = "Chilly";
        SeasonPhaseIndexModiferDegress = (SeasonPhaseIndex - 1 ) * -1;
        TemperatureVarianceDicePool = 2;
        TemperatureVarianceDiceSize = 8;
        BaseSeasonTemperatureDegrees = -2;  // avg 9 + -2 = 7 
        CloudCoverModifier = +1;
      break;

      case 3:
        // winter
        TempCategoryText = "Cold";
        SeasonPhaseIndexModiferDegress = (SeasonPhaseIndex - 1 ) * -1;
        TemperatureVarianceDicePool = 2;
        TemperatureVarianceDiceSize = 8;
        BaseSeasonTemperatureDegrees = -22;  // avg 9 + -22
        CloudCoverModifier = +2;
      break;
    } // end setDefaultConditionsForSeason

    SeasonPhaseIndexModiferDegress = SeasonPhaseIndexModiferDegress * 3;
    TemperatureVarianceDiceRoll = dice(TemperatureVarianceDicePool, TemperatureVarianceDiceSize) + BaseSeasonTemperatureDegrees +SeasonPhaseIndexModiferDegress;

    TempDiceRoll = Math.max ( ( dice(2,4) + CloudCoverModifier ), 0 );
    TempDiceRoll = Math.min ( TempDiceRoll, 8);

    CloudCoverText = Integer.toString(TempDiceRoll) + "/8";
    if (CloudCoverText == "0/8")
      CloudCoverText = "little to no";
    if (CloudCoverText == "8/8")
      CloudCoverText = "heavy to total";

    WindDataText = getRandomWindData();

    WorkPhrase = MessageFormat.format(CurrentConditionsFormatString, TempCategoryText, TemperatureVarianceDiceRoll, CloudCoverText, WindDataText);

    DefaultConditionsForSeason = WorkPhrase;
  } // setDefaultConditionsForSeason

  private String getRandomWindData()
  {
    String WindStrength = "";
    String WindDirection = "";
    int DiceRoll = 0;

    DiceRoll = dice(2,6);
    if ( DiceRoll == 2 )
      WindStrength = "howling";

    if ( DiceRoll == 3 )
      WindStrength = "harsh";

    if ( 4 <= DiceRoll && DiceRoll <= 5 )
      WindStrength = "medium";

    if ( 6 <= DiceRoll && DiceRoll <= 8 )
      WindStrength = "breezy";

    if ( 9 <= DiceRoll && DiceRoll <= 10 )
      WindStrength = "light";

    if ( DiceRoll == 11 )
      WindStrength = "strong";

    if ( DiceRoll == 12 )
      WindStrength = "stinging";

    DiceRoll = dice(2,6);
    if ( DiceRoll == 2 )
      WindDirection = "North-Westerly";

    if ( DiceRoll == 3 )
      WindDirection = "Northerly";

    if ( 4 <= DiceRoll && DiceRoll <= 5 )
      WindDirection = "North-Easterly";

    if ( 6 <= DiceRoll && DiceRoll <= 8 )
      WindDirection = "Easterly";

    if ( 9 <= DiceRoll && DiceRoll <= 10 )
      WindDirection = "South-Easterly";

    if ( DiceRoll == 11 )
      WindDirection = "Southerly";

    if ( DiceRoll == 12 )
      WindDirection = "South-Westerly";

    return WindDirection + " and " + WindStrength ;
  } // end getRandomWindData

  private String getWeatherEffectsEncountersBySeason()
  {
    String EncounterWeatherEffectsText = "";

    int TableDiceRoll = dice (1,12);
    RollTables RollableTable = new RollTables();
    EncounterWeatherEffectsText = RollableTable.getResultFromTableRoll ( TableDiceRoll, "encounter_weather_" + getSeasonName() );

    return EncounterWeatherEffectsText;
  } // end getEncounterWeatherEffects

} // end class GetTravelReport
