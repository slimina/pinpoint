/**
 * Copyright 2014 NAVER Corp.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.navercorp.pinpoint.plugin.jetty;

import com.navercorp.pinpoint.bootstrap.plugin.ApplicationTypeDetector;
import com.navercorp.pinpoint.bootstrap.resolver.ConditionProvider;
import com.navercorp.pinpoint.common.trace.ServiceType;

import java.util.Arrays;
import java.util.List;

public class JettyDetector implements ApplicationTypeDetector {

    private static final String DEFAULT_BOOTSTRAP_MAIN = "org.eclipse.jetty.start.Main";

    private final List<String> bootstrapMains;

    public JettyDetector(List<String> bootstrapMains) {
        if (bootstrapMains == null || bootstrapMains.isEmpty()) {
            this.bootstrapMains = Arrays.asList(DEFAULT_BOOTSTRAP_MAIN);
        } else {
            this.bootstrapMains = bootstrapMains;
        }
    }

    @Override
    public ServiceType getApplicationType() {
        return JettyConstants.JETTY;
    }

    @Override
    public boolean detect(ConditionProvider provider) {
        return provider.checkMainClass(bootstrapMains);
    }
}
