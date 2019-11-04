<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
    crossorigin="anonymous">
    
    <sb:head/>
    <title><s:text name = "app.title" /></title>
  </head>

  <body>
    <div class="container">

      <h1><s:text name = "h1.welcome" /></h1>

      <div class="row">
        <div class="col-sm-6">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title"><s:text name = "p.card.title" /></h5>
              <h6 class="card-subtitle mb-2 text-muted"><s:text name = "p.card.subtitle" /></h6>

              <div class="row">
                <div class="col-md">
                  <s:text name = "p.label.SeasonName" />
                </div>
                <div class="col-md">
                  <s:property value = "SeasonPhaseName" /> <s:property value = "SeasonName" />
                </div>
              </div>

              <div class="row">
                <div class="col-md">
                  <s:text name = "p.label.CivilizationDistanceKM" />
                </div>
                <div class="col-md">
                  <s:property value = "CivilizationDistanceKM" /> Km
                </div>
              </div>

              <div class="row">
                <div class="col-md">
                  <s:text name = "p.label.DefaultConditions" />
                </div>
                <div class="col-md">
                  <s:property value = "DefaultConditionsForSeason" />
                </div>
              </div>

              <s:if test = "EncounterMonsterCR != null && EncounterMonsterCR != ''">
                <div class="row">
                  <div class="col-md">
                    <s:text name = "p.label.EncounterMonsterCR" />
                  </div>
                  <div class="col-md">
                    <s:property value = "EncounterMonsterCR" /> (based on distance from patrols, etc)
                  </div>
                </div>
              </s:if>

              <s:if test = "EncounterWeatherEffects != null && EncounterWeatherEffects != ''">
                <div class="row">
                  <div class="col-md">
                    <s:text name = "p.label.EncounterWeatherEffects" />
                  </div>
                  <div class="col-md">
                    <s:property value = "EncounterWeatherEffects" />
                  </div>
                </div>
              </s:if>

            </div>
          </div>
        </div>
      </div>

      <hr />
      <s:form action = "GetTravelReport" namespace = "/" method = "post" name = "DoWeather" theme = "bootstrap">
        <s:submit key = "SubmitPressed" type = "button" cssClass = "btn btn-outline-primary"/>
      </s:form>

    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" 
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" 
    crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" 
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" 
    crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" 
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" 
    crossorigin="anonymous"></script>
  </body>
</html>
