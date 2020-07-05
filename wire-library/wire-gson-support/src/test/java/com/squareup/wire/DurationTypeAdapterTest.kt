/*
 * Copyright 2020 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.wire

import com.squareup.wire.DurationTypeAdapter.durationToString
import com.squareup.wire.DurationTypeAdapter.stringToDuration
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DurationTypeAdapterTest {
  @Test
  fun `string to duration`() {
    assertThat(stringToDuration("1s")).isEqualTo(durationOfSeconds(1L, 0L))
    assertThat(stringToDuration("1.00000000200s")).isEqualTo(durationOfSeconds(1L, 2L))
    assertThat(stringToDuration("1.0000000020s")).isEqualTo(durationOfSeconds(1L, 2L))
    assertThat(stringToDuration("1.000000002s")).isEqualTo(durationOfSeconds(1L, 2L))
    assertThat(stringToDuration("1.000002s")).isEqualTo(durationOfSeconds(1L, 2000L))
    assertThat(stringToDuration("1.002s")).isEqualTo(durationOfSeconds(1L, 2000000L))
    assertThat(stringToDuration("1.2s")).isEqualTo(durationOfSeconds(1L, 200000000L))
    assertThat(stringToDuration("-1.2s")).isEqualTo(durationOfSeconds(-1L, -200000000L))
    assertThat(stringToDuration("-0.2s")).isEqualTo(durationOfSeconds(0L, -200000000L))
    assertThat(stringToDuration("0.2s")).isEqualTo(durationOfSeconds(0L, 200000000L))
    assertThat(stringToDuration("-9223372036854775808s")).isEqualTo(
        durationOfSeconds(Long.MIN_VALUE, 0L))
    assertThat(stringToDuration("9223372036854775807.999999999s"))
        .isEqualTo(durationOfSeconds(Long.MAX_VALUE, 999_999_999L))
    assertThat(stringToDuration("-9223372036854775807.999999999s"))
        .isEqualTo(durationOfSeconds(-9223372036854775807L, -999_999_999L))
  }

  @Test
  fun `duration to string`() {
    assertThat(durationToString(durationOfSeconds( 0L,             0L))).isEqualTo("0s")
    assertThat(durationToString(durationOfSeconds( 1L,             0L))).isEqualTo("1s")
    assertThat(durationToString(durationOfSeconds( 1L,             2L))).isEqualTo( "1.000000002s")
    assertThat(durationToString(durationOfSeconds( 1L,            20L))).isEqualTo( "1.000000020s")
    assertThat(durationToString(durationOfSeconds( 1L,         2_000L))).isEqualTo( "1.000002s")
    assertThat(durationToString(durationOfSeconds( 1L,     2_000_000L))).isEqualTo( "1.002s")
    assertThat(durationToString(durationOfSeconds( 1L,   200_000_000L))).isEqualTo( "1.200s")
    assertThat(durationToString(durationOfSeconds(-1L,  -200_000_000L))).isEqualTo("-1.200s")
    assertThat(durationToString(durationOfSeconds( 0L,  -200_000_000L))).isEqualTo("-0.200s")
    assertThat(durationToString(durationOfSeconds( 0L,  -999_999_999L))).isEqualTo("-0.999999999s")
    assertThat(durationToString(durationOfSeconds( 0L,  -999_999_000L))).isEqualTo("-0.999999s")
    assertThat(durationToString(durationOfSeconds( 0L,  -999_900_000L))).isEqualTo("-0.999900s")
    assertThat(durationToString(durationOfSeconds( 0L,  -999_000_000L))).isEqualTo("-0.999s")
    assertThat(durationToString(durationOfSeconds(Long.MIN_VALUE, 0L))).isEqualTo("-9223372036854775808s")
    assertThat(durationToString(durationOfSeconds(Long.MIN_VALUE, 1L)))
        .isEqualTo("-9223372036854775807.999999999s")
  }
}
