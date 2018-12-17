/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
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

package org.kie.api.internal.utils;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import org.kie.api.definition.KieDescr;
import org.kie.api.io.KieResources;
import org.kie.api.io.Resource;

public class MockKieResources implements KieResources {
    public Resource newUrlResource(URL url) {
        return null;
    }

    public Resource newUrlResource(URL url,
                            String encoding) {
        return null;
    }

    public Resource newUrlResource(String path) {
        return null;
    }

    public Resource newUrlResource(String path,
                            String encoding) {
        return null;
    }

    public Resource newFileSystemResource(File file) {
        return null;
    }

    public Resource newFileSystemResource(File file,
                                   String encoding) {
        return null;
    }

    public Resource newFileSystemResource(String fileName) {
        return null;
    }

    public Resource newFileSystemResource(String fileName,
                                   String encoding) {
        return null;
    }

    public Resource newByteArrayResource(byte[] bytes) {
        return null;
    }

    public Resource newByteArrayResource(byte[] bytes,
                                  String encoding) {
        return null;
    }

    public Resource newInputStreamResource(InputStream stream) {
        return null;
    }

    public Resource newInputStreamResource(InputStream stream,
                                    String encoding) {
        return null;
    }

    public Resource newReaderResource(Reader reader) {
        return null;
    }

    public Resource newReaderResource(Reader reader,
                               String encoding) {
        return null;
    }

    public Resource newClassPathResource(String path) {
        return null;
    }

    public Resource newClassPathResource(String path,
                                  ClassLoader classLoader) {
        return null;
    }

    public Resource newClassPathResource(String path,
                                  Class<?> clazz) {
        return null;
    }

    public Resource newClassPathResource(String path,
                                  String encoding) {
        return null;
    }

    public Resource newClassPathResource(String path,
                                  String encoding,
                                  ClassLoader classLoader) {
        return null;
    }

    public Resource newClassPathResource(String path,
                                  String encoding,
                                  Class<?> clazz) {
        return null;
    }

    public Resource newDescrResource( KieDescr descr ) {
        return null;
    }
}
