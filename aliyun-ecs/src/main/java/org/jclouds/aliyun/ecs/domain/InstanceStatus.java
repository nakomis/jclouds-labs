/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.aliyun.ecs.domain;

import com.google.auto.value.AutoValue;
import com.google.common.base.Enums;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import org.jclouds.json.SerializedNames;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * https://www.alibabacloud.com/help/doc-detail/25687.htm?spm=a2c63.p38356.a3.3.25a15566k0U5A4
 */
@AutoValue
public abstract class InstanceStatus {

   InstanceStatus() {
   }

   @SerializedNames({ "InstanceId", "Status" })
   public static InstanceStatus create(String instanceId, Status status) {
      return new AutoValue_InstanceStatus(instanceId, status);
   }

   public abstract String instanceId();

   public abstract Status status();

   public enum Status {
      PENDING, STARTING, RUNNING, STOPPING, STOPPED;

      public static InstanceStatus.Status fromValue(String value) {
         Optional<Status> status = Enums.getIfPresent(InstanceStatus.Status.class, value.toUpperCase());
         checkArgument(status.isPresent(), "Expected one of %s but was %s",
               Joiner.on(',').join(InstanceStatus.Status.values()), value);
         return status.get();
      }
   }
}
