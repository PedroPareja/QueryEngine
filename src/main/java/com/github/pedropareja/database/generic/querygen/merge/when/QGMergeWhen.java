package com.github.pedropareja.database.generic.querygen.merge.when;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.merge.insert.QGLinkMergeInsert;
import com.github.pedropareja.database.generic.querygen.merge.update.QGLinkMergeUpdate;

public interface QGMergeWhen extends QGQuery, QGLinkMergeInsert, QGLinkMergeUpdate
{
    enum QGMergeWhenType
    {
        MATCHED("MATCHED"),
        NOT_MATCHED("NOT MATCHED");

        private final String sqlText;

        QGMergeWhenType(String sqlText)
        {
            this.sqlText = sqlText;
        }

        public String getSqlText()
        {
            return sqlText;
        }
    }
}
