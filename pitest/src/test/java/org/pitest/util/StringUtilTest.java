/*
 * Copyright 2011 Henry Coles
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.pitest.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class StringUtilTest {

  @Test
  public void shouldCreateEmptyStringWhenJoiningEmptyList() {
    assertEquals("", StringUtil.join(Collections.emptyList(), ","));
  }

  @Test
  public void shouldIncludeNoSeparatorsWhenJoiningSingleItem() {
    assertEquals("foo", StringUtil.join(Collections.singleton("foo"), ","));
  }

  @Test
  public void shouldJoinStringWithSeparators() {
    assertEquals("foo,bar,car",
        StringUtil.join(Arrays.asList("foo", "bar", "car"), ","));
  }

  @Test
  public void repeatShouldRepeatGivenCharacter() {
    assertEquals("----", StringUtil.repeat('-', 4));
  }

}
