/*
 * Copyright 2016 htouhui.com All right reserved. This software is the
 * confidential and proprietary information of htouhui.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with haitouhui.com.
 */
package com.example.vegetables.util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author redstarstar, star.hong@gmail.com
 * @version 1.0
 */
public class OSSPath {

    private OSSClientFactory.Directory directory;
    private OSSClientFactory.DataType dataType;
    private String dataId;
    private String filename;

    public void setDirectory(OSSClientFactory.Directory directory) {
        this.directory = directory;
    }

    public void setDataType(OSSClientFactory.DataType dataType) {
        this.dataType = dataType;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return new StringBuilder(directory.getPrefix())
                .append("/").append(dataType.getPrefix())
                .append("/").append(dataId)
                .append("/").append(filename)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof OSSPath)) {
            return false;
        }

        OSSPath ossPath = (OSSPath) o;

        return new EqualsBuilder()
                .append(directory, ossPath.directory)
                .append(dataType, ossPath.dataType)
                .append(dataId, ossPath.dataId)
                .append(filename, ossPath.filename)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(directory)
                .append(dataType)
                .append(dataId)
                .append(filename)
                .toHashCode();
    }
}
