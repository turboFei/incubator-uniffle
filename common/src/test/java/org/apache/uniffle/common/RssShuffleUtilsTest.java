/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.uniffle.common;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RssShuffleUtilsTest {
  @Test
  public void compressionTest() {
    List<Integer> testSizes = Lists.newArrayList(
      1, 1024, 128 * 1024, 512 * 1024, 1024 * 1024, 4 * 1024 * 1024);
    for (int size : testSizes) {
      singleTest(size);
    }
  }

  private void singleTest(int size) {
    byte[] buf = new byte[size];
    new Random().nextBytes(buf);

    byte[] compressed = RssShuffleUtils.compressData(buf);
    byte[] uncompressed = RssShuffleUtils.decompressData(compressed, size);
    assertArrayEquals(buf, uncompressed);
  }
}
