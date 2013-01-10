/*
 * Copyright (c) 2012 SnowPlow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package com.snowplowanalytics.snowplow.hadoop.etl
package enrichments

// Java
import java.util.UUID

// Scalaz
import scalaz._
import Scalaz._

/**
 * Holds the enrichments related to events.
 */
object EventEnrichments {

  /**
   * Turns an event code into a valid event type,
   * e.g. "pv" -> "page_view".
   *
   * @param eventCode The event code
   * @return the event type, or an error message
   *         if not recognised, boxed in a Scalaz
   *         Validation
   */
  def extractEventType(eventCode: String): Validation[String, String] = eventCode match {
    case "ev" => "custom".success
    case "ad" => "ad_impression".success
    case "tr" => "transaction".success
    case "ti" => "transaction_item".success
    case "pv" => "page_view".success
    case "pp" => "page_ping".success
    case _    => "[%s] is not a recognised event code".format(eventCode).fail
  }

  /**
   * Returns a unique event ID. The event ID is 
   * generated as a type 4 UUID, then converted
   * to a String.
   *
   * () on the function signature because it's
   * not pure
   *
   * @return the event ID
   */
  def generateEventId(): String = UUID.randomUUID().toString
}