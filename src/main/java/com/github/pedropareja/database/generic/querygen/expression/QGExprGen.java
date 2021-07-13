package com.github.pedropareja.database.generic.querygen.expression;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.DBTable;
import com.github.pedropareja.database.generic.querygen.Order;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.expression.aggregate.QGAggregate;
import com.github.pedropareja.database.generic.querygen.expression.aggregate.QGAggregate.Type;
import com.github.pedropareja.database.generic.querygen.expression.aggregate.QGAggregatePrv;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.casecmd.QGCase;
import com.github.pedropareja.database.generic.querygen.expression.casecmd.QGCasePrv;
import com.github.pedropareja.database.generic.querygen.expression.coalesce.QGCoalesce;
import com.github.pedropareja.database.generic.querygen.expression.coalesce.QGCoalescePrv;
import com.github.pedropareja.database.generic.querygen.expression.count.QGCount;
import com.github.pedropareja.database.generic.querygen.expression.count.QGCountPrv;
import com.github.pedropareja.database.generic.querygen.expression.field.QGField;
import com.github.pedropareja.database.generic.querygen.expression.field.QGFieldPrv;
import com.github.pedropareja.database.generic.querygen.expression.function.QGFunction;
import com.github.pedropareja.database.generic.querygen.expression.function.QGFunctionPrv;
import com.github.pedropareja.database.generic.querygen.expression.id.QGId;
import com.github.pedropareja.database.generic.querygen.expression.id.QGIdPrv;
import com.github.pedropareja.database.generic.querygen.expression.number.QGNumber;
import com.github.pedropareja.database.generic.querygen.expression.number.QGNumberPrv;
import com.github.pedropareja.database.generic.querygen.expression.param.QGParam;
import com.github.pedropareja.database.generic.querygen.expression.param.QGParamPrv;
import com.github.pedropareja.database.generic.querygen.expression.parenthesis.QGParenthesis;
import com.github.pedropareja.database.generic.querygen.expression.parenthesis.QGParenthesisPrv;
import com.github.pedropareja.database.generic.querygen.expression.subquery.QGSubQuery;
import com.github.pedropareja.database.generic.querygen.expression.subquery.QGSubQueryPrv;
import com.github.pedropareja.database.generic.querygen.expression.table.QGTable;
import com.github.pedropareja.database.generic.querygen.expression.table.QGTablePrv;
import com.github.pedropareja.database.generic.querygen.orderby.QGOrderElement;
import com.github.pedropareja.database.generic.querygen.orderby.QGOrderElementPrv;

import java.math.BigDecimal;

public class QGExprGen
{
    private QGExprGen() {}

    public static QGAggregate avg(QGExpression exp)
    {
        return new QGAggregatePrv(Type.AVG, exp);
    }

    public static QGCount count(QGExpression ... parameters)
    {
        return new QGCountPrv(parameters);
    }

    public static QGCount count() { return new QGCountPrv(); }

    public static QGAggregate min(QGExpression exp)
    {
        return new QGAggregatePrv(QGAggregate.Type.MIN, exp);
    }

    public static QGAggregate max(QGExpression exp)
    {
        return new QGAggregatePrv(QGAggregate.Type.MAX, exp);
    }

    public static QGAggregate sum(QGExpression exp)
    {
        return new QGAggregatePrv(QGAggregate.Type.SUM, exp);
    }

    public static QGField field(DBFieldInfo fieldValue)
    {
        return new QGFieldPrv(fieldValue);
    }

    public static QGId id(String idValue)
    {
        return new QGIdPrv(idValue);
    }

    @SafeVarargs
    public static QGCoalesce coalesce(QGExpression... parameters)
    {
        return new QGCoalescePrv(parameters);
    }

    public static QGNumber number(BigDecimal value)
    {
        return new QGNumberPrv(value);
    }

    public static QGNumber number(int value)
    {
        return new QGNumberPrv(value);
    }

    public static QGNumber number(String value)
    {
        return new QGNumberPrv(value);
    }

    public static QGParenthesis parenthesis(QGExpression content) { return new QGParenthesisPrv(content); }

    public static QGCase caseSelection() { return new QGCasePrv(); }

    public static QGOrderElement order(QGExpression exp, Order orderType) { return new QGOrderElementPrv(exp, orderType); }

    public static QGOrderElement order(QGExpression exp) { return new QGOrderElementPrv(exp); }

    public static QGParam param() { return new QGParamPrv(); }

    public static QGParam params(int repetitions) { return new QGParamPrv(repetitions); }

    @SafeVarargs
    public static QGFunction function(String functionName, QGExpression... parameters) { return new QGFunctionPrv(functionName, parameters); }

    public static QGSubQuery subQuery(QGQuery query) { return new QGSubQueryPrv(query); }

    public static QGTable table(DBTable table) { return new QGTablePrv(table); }
}

