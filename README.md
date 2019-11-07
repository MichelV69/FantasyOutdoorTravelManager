# Fantasy Outdoor Travel Manager

***For Fantasy Worlds Settings such as Dungeons & Dragons 5th Edition***

## Current Version
1.1.1

## Purpose
Most Games Masters or Authors are aware that they need to include weather
events in their setting outdoor travel narrative.  However, it can be awkward
to do so easily without complex table rolling that interrupts the flow of the 
story.

Based on [this Reddit post](https://www.reddit.com/r/mattcolville/comments/5xa9wp/weather_in_dungeons_and_dragons/)
I decided to put together a simple JAVA/Tomcat application that would quickly
handle this job.

## Sample Output

| Travel Report                               |                                 |
| ------------------------------------------- | ------------------------------  |
| Covering the next 4 hours of travel or rest |                                 |
| Current Season                              | Mid Summer                      |
| Distance from the nearest patrol, etc       | 123 Km (Normal risk-level area) |
| Current seasonal conditions                 | Warm (around 19C), 0/8 cloud cover, with wind North-Easterly and breezy. |
| (Potential) Monster Encounter               | CR2 to CR6 (based on distance from patrols, etc) |
| (Potential) Notable Weather Effects         | Rain. Every square or kilometre of movement costs an additional square or kilometre of movement. Disadvantage on Constitution checks caused by cold weather. Rain lasts until the next overland travel roll.|

## How To Use
* Download WAR file, or source and compile your own
* Copy to the `WEBAPPS` folder of your Tomcat server.
* Restart the Tomcat process as required
* Point your favorite web browser to the running Tomcat server.  For example http://localhost:8080/FantasyOutdoorTravelManager/
* Enter the data corresponding to current setting circumstances:
  * Select the relative combat-encounter risk for the area.
    * The default ("***Normal***") is a 2-in-12 chance of a possible combat encounter.
    * "***Dangerous***" increases this to 3-in-12.
    * "***Savage***" increases this to 4-in-12.
  * Select the current season (***"Spring"***, ***"Summer"***, ***"Autumn"***, 
  ***"Winter"***).  This will indicate the primary type of current weather.
  * Select the phase of the season (***"Early"***, ***"Mid"***, ***"Late"***).
  This will modify the current temperature of the current season.
  * Enter the distance in kilometers of the current position of the story 
  characters, relative to the nearest active civilization, such as towns, 
  villages or regularly patrolled highways.
  * Click `[Get Travel Report]`.
  * Review results.  Discard anything you don't think fits.
  * Click `[Get Travel Report]` to re-roll with the same data, or
  * Click `[Enter new conditions]` to start over.
