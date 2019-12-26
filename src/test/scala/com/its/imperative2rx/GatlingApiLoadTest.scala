package com.its.imperative2rx

//import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import java.util.Date

import io.gatling.core.structure.ScenarioBuilder

//import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration._
import java.util.concurrent.TimeUnit

//import io.gatling.core.structure.ScenarioBuilder

class GatlingApiLoadTest extends Simulation {

  val rampUpTimeSecs = 5
  val testTimeSecs = 20
  val noOfUsers = 10
  /*val minWaitMs = 1000 toMillis
  val maxWaitMs = 3000 milliseconds*/

  val baseURL = "http://localhost:8090"
  val baseNameForFetchAllCards = "fetch-all-cards-4"
  val requestName = baseNameForFetchAllCards + "-request"
  val scenarioName = baseNameForFetchAllCards + "-scenario"
  val fetchAllCardsURI = "/cards"
  val addCardURI = "/card"
  val fetchCardURI = "/card"

  val scn = scenario("AddAndFindCard-100r-1500cu")
              .repeat(100, "n") (
                exec(
                  http("AddCard-API")
                    .post(baseURL+addCardURI)
                    .header("Content-Type", "application/json")
                    .body(StringBody("""{"issuingNetwork":"MC-${n}","cardNumber":"345678932165498${n}","name":"dps-${n}","expiryDate":"2311"}"""))
                    .check(status.is(200))
                ).pause(Duration.apply(5, TimeUnit.MILLISECONDS))
              ).repeat(100, "n") (//{
                exec(
                  http("GetCard-API")
                    .get(baseURL+fetchCardURI+"/${n}")
                    .check(status.is(200))
                )
              )

  setUp(scn
    .inject(atOnceUsers(1500))
    ).maxDuration(FiniteDuration
    .apply(5, "minutes")
  )



}