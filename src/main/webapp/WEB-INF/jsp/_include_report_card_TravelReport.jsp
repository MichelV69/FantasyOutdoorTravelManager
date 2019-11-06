<%@ page contentType = "text/html; charset = UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags"%>
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
                <s:property value = "CivilizationDistanceKM" /> Km (<s:property value = "EncounterRisk" /> risk-level area)
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
