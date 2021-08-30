package com.github.pedropareja.database.generic.querygen.expression.id;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import com.github.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QGIdPrv extends QGExpressionBase
        implements QGId, QGLinkOperatorsPrv, QGLinkAsPrv
{
    private final String id;

    private final static Pattern pattern = Pattern.compile("[-\\(\\)\\+<>*\\\\|/\\[\\]@$&\\!#%\"\\?' ]+");
    private final Matcher matcher;

    public QGIdPrv(QGExpressionPrv init, String id)
    {
        super(init);
        this.id = id;

        this.matcher = pattern.matcher(id);
    }

    public QGIdPrv(String id)
    {
        super();
        this.id = id;

        this.matcher = pattern.matcher(id);
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context)
    {
        printSpaceIfNotFirst(stringBuilder);

        // check for special characters
        if (matcher.find())
            stringBuilder.append("\"").append(id).append("\"");
        else
            stringBuilder.append(id);
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        return null;
    }
}
