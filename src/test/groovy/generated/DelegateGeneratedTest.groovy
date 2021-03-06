/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package groovy.generated

import groovy.transform.CompileStatic
import org.junit.Test

@CompileStatic
class DelegateGeneratedTest extends AbstractGeneratedAstTestCase {
    final Class<?> implicitDelegate = parseClass('''
      class ClassUnderTest {
          static class D {
              void m1() { }
              void m2() { }
          }

          @Delegate
          D delegate = new D()
      }''')

    final Class<?> explicitDelegate = parseClass('''
      class ClassUnderTest {
          static class D {
              void m1() { }
              void m2() { }
          }

          @Delegate
          D delegate = new D()

          void m1() { }
          // void m2() { }
      }''')

    @Test
    void test_m1_is_annotated() {
        assertMethodIsAnnotated(implicitDelegate, 'm1')
    }

    @Test
    void test_m2_is_annotated() {
        assertMethodIsAnnotated(implicitDelegate, 'm2')
    }

    @Test
    void test_explicit_m1_is_not_annotated() {
        assertMethodIsNotAnnotated(explicitDelegate, 'm1')
    }

    @Test
    void test_explicit_m2_is_annotated() {
        assertMethodIsAnnotated(explicitDelegate, 'm2')
    }
}