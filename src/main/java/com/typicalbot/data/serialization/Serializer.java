/**
 * Copyright 2016-2019 Bryan Pikaard & Nicholas Sylke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.typicalbot.data.serialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * @author TypicalBot
 * @since 3.0.0-alpha
 */
public class Serializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Serializer.class);

    public void serialize(Object object, OutputStream stream) throws IOException {
        if (!(object instanceof Serializable)) {
            throw new IllegalArgumentException(getClass().getSimpleName() + " requires a Serializable payload but received an object of type [" + object.getClass().getSimpleName() + "]");
        }

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    }
}
