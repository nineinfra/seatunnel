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

package org.apache.seatunnel.connectors.seatunnel.paimon.data;

import org.apache.seatunnel.api.table.catalog.Column;
import org.apache.seatunnel.api.table.catalog.PhysicalColumn;
import org.apache.seatunnel.api.table.converter.TypeConverter;
import org.apache.seatunnel.connectors.seatunnel.paimon.sink.PaimonSink;
import org.apache.seatunnel.connectors.seatunnel.paimon.utils.RowTypeConverter;

import org.apache.paimon.types.DataType;

import com.google.auto.service.AutoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutoService(TypeConverter.class)
public class PaimonTypeMapper implements TypeConverter<DataType> {
    public static final PaimonTypeMapper INSTANCE = new PaimonTypeMapper();

    @Override
    public String identifier() {
        return PaimonSink.PLUGIN_NAME;
    }

    @Override
    public Column convert(DataType dataType) {
        return PhysicalColumn.builder().dataType(RowTypeConverter.convert(dataType)).build();
    }

    @Override
    public DataType reconvert(Column column) {
        return RowTypeConverter.reconvert(column.getDataType());
    }
}
