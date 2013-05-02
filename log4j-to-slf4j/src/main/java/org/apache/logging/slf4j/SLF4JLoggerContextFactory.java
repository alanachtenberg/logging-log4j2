/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.apache.logging.slf4j;

import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextFactory;

import java.net.URI;

/**
 *
 */
public class SLF4JLoggerContextFactory implements LoggerContextFactory {
    private static LoggerContext context = new SLF4JLoggerContext();

    public SLF4JLoggerContextFactory() {
        // LOG4J2-230, LOG4J2-204 (improve error reporting when misconfigured)
        boolean misconfigured = false;
        try {
            Class.forName("org.slf4j.helpers.Log4JLoggerFactory");
            misconfigured = true;
        } catch (ClassNotFoundException classNotFoundIsGood) {
            // org.slf4j.helpers.Log4JLoggerFactory is not on classpath. Good!
        }
        if (misconfigured) {
            throw new IllegalStateException("slf4j-impl jar is mutually exclusive with log4j-to-slf4j jar "
                    + "(the first routes calls from SLF4J to Log4j, the second from Log4j to SLF4J)");
        }
    }

    public LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext) {
        return context;
    }

    public LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext,
                                    URI configLocation) {
        return context;
    }
}
