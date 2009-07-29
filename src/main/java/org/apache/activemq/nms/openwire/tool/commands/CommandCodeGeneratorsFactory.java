/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.nms.openwire.tool.commands;

/**
 * Provides methods to get a Source file and Header file Code generator given a Class
 * name.
 *
 * @since 2.0
 */
public class CommandCodeGeneratorsFactory {

    /**
     * Given a class name return an instance of a CSharp Class File Generator
     * that can generate the file for the Class.
     *
     * @param className - name of the class to find the generator for
     *
     * @return a new Header File code generator.
     */
    public CommandCodeGenerator getCodeGenerator( String className ) {

        Class<?> generatorClass = null;

        try {
            generatorClass = Class.forName( "org.apache.activemq.nms.openwire.tool.commands." + className + "Generator" );
        } catch (ClassNotFoundException e) {
            return new CommandClassGenerator();
        }

        CommandCodeGenerator generator;
        try {
            generator = (CommandCodeGenerator) generatorClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        return generator;
    }

}
