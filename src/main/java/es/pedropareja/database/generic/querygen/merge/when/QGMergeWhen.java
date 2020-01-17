package es.pedropareja.database.generic.querygen.merge.when;

import es.pedropareja.database.generic.querygen.base.QGQuery;

public interface QGMergeWhen extends QGQuery
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
