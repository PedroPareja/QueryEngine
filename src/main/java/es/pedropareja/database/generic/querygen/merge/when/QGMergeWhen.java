package es.pedropareja.database.generic.querygen.merge.when;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.merge.insert.QGLinkMergeInsert;
import es.pedropareja.database.generic.querygen.merge.update.QGLinkMergeUpdate;

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
