<%@ page contentType = "text/html; charset = UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags"%>

<s:form name = "GetUserInfo" namespace = "/" action = "GetTravelReport" method = "post" theme = "bootstrap">

    <s:radio name = "EncounterRisk"
             label = "Pick the relative combat-encounter risk for the area (+1/12 chance & +1CR per level above \"normal\") "
             list = "EncounterRiskLevels"
             value = "EncounterRiskLevels[0]" />

    <s:select name = "SeasonName"
              label = "Pick the current season"
              headerKey = "-1"
              headerValue = "(current season)"
              list = "ListOfSeasons"
              value = "-1" />

    <s:select name = "SeasonPhaseName"
              label = "Pick the current phase of the season"
              headerKey = "-1"
              headerValue = "(current phase)"
              list = "PhasesOfSeasons"
              value = "-1" />

    <s:textfield name = "CivilizationDistanceKM" label = "Distance in Km to nearest road or settlement" />

    <s:submit key = "SubmitPressed" type = "button" cssClass = "btn btn-primary" cssRole = "button" />
</s:form>