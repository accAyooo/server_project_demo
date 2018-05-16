package com.accAyo.serverProjectDemo.service.HibernateBaseService;

import com.accAyo.serverProjectDemo.common.CompareType;
import com.accAyo.serverProjectDemo.common.LogicalType;
import com.accAyo.serverProjectDemo.util.ResultFilter;
import com.accAyo.serverProjectDemo.util.ResultFilter1;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:35 2018/5/16
 */

@Transactional(readOnly = false)
public abstract class BaseService {

    protected final Logger logger = Logger.getLogger(getClass());

    @Resource
    private HibernateGenericController hibernateGenericController;

    protected static HashMap<String, String> objectCacheLock = new HashMap<String, String>();

    public HibernateGenericController getHibernateGenericController() {
        return this.hibernateGenericController;
    }

    public void setHibernateGenericController(HibernateGenericController hibernateGenericController) {
        this.hibernateGenericController = hibernateGenericController;
    }

    // ====== ====== ====== ====== ====== ======

    protected <T> T getObject(Class<T> clazz, Serializable id) {
        return getHibernateGenericController().get(clazz, id);
    }

    protected void addObject(Object entity) {
        getHibernateGenericController().save(entity);
    }

    protected void updateObject(Object entity) {
        getHibernateGenericController().update(entity);
    }

    protected void deleteObject(Object entity) {
        getHibernateGenericController().delete(entity);
    }

    @SuppressWarnings("rawtypes")
    protected void deleteObjects(Collection collection) {
        getHibernateGenericController().delete(collection);
    }

    @SuppressWarnings("rawtypes")
    protected void evictObjects(Class clazz, Serializable id) {
        getHibernateGenericController().evict(clazz, id);
    }

    @SuppressWarnings("rawtypes")
    protected int getObjectsCount(Class clazz,
                                  Collection<HibernateExpression> expressions) {
        int totalCount = 0;
        if (expressions == null) {
            expressions = new ArrayList<HibernateExpression>();
        }
        totalCount = getHibernateGenericController().getResultCount(clazz,	expressions).intValue();
        return totalCount;
    }

    protected <T> ResultFilter<T> getObjects(Class<T> clazz,
                                             Collection<HibernateExpression> expressions, int pageSize,
                                             int currentPage) {
        return getObjects(clazz, expressions, pageSize, currentPage, false,
                "id");
    }

    @SuppressWarnings("unchecked")
    protected <T> ResultFilter<T> getObjects(Class<T> clazz,
                                             Collection<HibernateExpression> expressions, int pageSize,
                                             int currentPage, boolean isAsc, String... orderBys) {
        int totalCount = getObjectsCount(clazz, expressions);
        ResultFilter<T> resultFilter = new ResultFilter<T>(totalCount,
                pageSize, currentPage);
        List<T> items = new ArrayList<T>();
        if (totalCount > 0) {
            items = getHibernateGenericController().findBy(clazz,
                    resultFilter.getCurrentPage(), resultFilter.getPageSize(),
                    expressions, isAsc, orderBys);
        }
        resultFilter.setItems(items);
        return resultFilter;
    }

    //新增方法，返回ResultFilter1
    @SuppressWarnings("unchecked")
    protected <T> ResultFilter1<T> getObjects1(Class<T> clazz,
                                               Collection<HibernateExpression> expressions, int pageSize,
                                               int currentPage, boolean isAsc, String... orderBys) {
        int totalCount = getObjectsCount(clazz, expressions);
        ResultFilter1<T> resultFilter = new ResultFilter1<T>(totalCount,
                pageSize, currentPage);
        List<T> items = null;
        if (totalCount > 0) {
            items = getHibernateGenericController().findBy(clazz,
                    resultFilter.getCurrentPage(), resultFilter.getPageSize(),
                    expressions, isAsc, orderBys);
        }
        if (items == null)
            items = new ArrayList<T>();
        resultFilter.setItems(items);
        return resultFilter;
    }

    @SuppressWarnings("unchecked")
    protected <T> List<T> getObjectsWithStart(Class<T> clazz,
                                              Collection<HibernateExpression> expressions, int startIndex,
                                              int rowSize, boolean isAsc, String... orderBys) {
        // 增加用户权限判断
        if (expressions == null) {
            expressions = new ArrayList<HibernateExpression>();
        }
        return getHibernateGenericController().findByWithStart(clazz,
                expressions, startIndex, rowSize, isAsc, orderBys);
    }

    protected <T> boolean isUnique(Class<T> clasz, Object entity,
                                   String uniquePropertyNames) {
        return getHibernateGenericController().isUnique(clasz, entity,
                uniquePropertyNames);
    }

    @SuppressWarnings("rawtypes")
    protected List findBy(String hql, Object... values) {
        return getHibernateGenericController().findBy(hql, values);
    }

    public Long getResultCount(String hql, Object... values) {
        return getHibernateGenericController().getResultCount(hql, values);
    }

    protected <T> T getObjectByProperty(Class<T> clazz, String property,
                                        Object value) {
        return getHibernateGenericController().findUniqueBy(clazz, property,
                value);
    }

    @SuppressWarnings("rawtypes")
    protected int getObjectsCountByProperty(Class clazz, String property,
                                            Object value) {
        HashMap<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put(property, value);
        return getObjectsCountByProperty(clazz, propertyMap, CompareType.Equal);
    }

    @SuppressWarnings("rawtypes")
    protected int getObjectsCountByProperty(Class clazz,
                                            HashMap<String, Object> propertyMap, CompareType compareType) {
        int count = 0;
        Collection<HibernateExpression> expressions = formExpressionsByProperty(
                propertyMap, compareType);
        count = getObjectsCount(clazz, expressions);
        return count;
    }

    protected <T> ResultFilter<T> getObjectsByProperty(Class<T> clazz,
                                                       String property, Object value, CompareType compareType,
                                                       int pageSize, int pageNo, boolean isAsc, String... orderBys) {
        HashMap<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put(property, value);
        return getObjectsByProperty(clazz, propertyMap, compareType, pageSize,
                pageNo, isAsc, orderBys);
    }

    protected <T> ResultFilter<T> getObjectsByProperty(Class<T> clazz,
                                                       HashMap<String, Object> propertyMap, CompareType compareType,
                                                       int pageSize, int pageNo, boolean isAsc, String... orderBys) {
        Collection<HibernateExpression> expressions = formExpressionsByProperty(
                propertyMap, compareType);
        return getObjects(clazz, expressions, pageSize, pageNo, isAsc, orderBys);
    }

    protected Collection<HibernateExpression> formExpressionsByProperty(
            HashMap<String, Object> propertyMap, CompareType compareType) {
        Collection<HibernateExpression> expressions = new ArrayList<HibernateExpression>();
        Iterator<String> keys = propertyMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = propertyMap.get(key);
            if (value != null) {
                HibernateExpression expression = new CompareExpression(key,
                        value, compareType);
                expressions.add(expression);
            }
        }
        return expressions;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected List findBy(final String selectProperty, final String className,
                          final Collection<HibernateExpression> expressions,
                          final int pageSize, final int pageNo, final boolean isAsc,
                          final String... orderBys) {
        return (List) getHibernateGenericController().getHibernateTemplate()
                .execute(new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        String tempSelPro = selectProperty;
                        if (StringUtils.isBlank(tempSelPro)) {
                            tempSelPro = "*";
                        }
                        List<String> valueList = new ArrayList<String>();
                        StringBuilder hql = new StringBuilder();
                        // 添加要检索的字段
                        hql.append("select ").append(tempSelPro);
                        // 添加要检索的类
                        hql.append(" from " + className);
                        // 添加检索条件
                        if (expressions != null && expressions.size() > 0) {
                            hql.append(" where");
                            Iterator<HibernateExpression> iterator = expressions
                                    .iterator();
                            HibernateExpression expression = null;
                            while (iterator.hasNext()) {
                                expression = iterator.next();
                                LogicalType logicType = null;
                                // 判断后面是否还是条件
                                if (iterator.hasNext()) {
                                    // 添加连接条件 and
                                    logicType = LogicalType.And;
                                }
                                // 构造hql
                                hql.append(formHibernateExpression2Hql(

                                        expression, valueList, logicType));
                            }
                        }
                        // 添加排序条件
                        if (orderBys != null && orderBys.length > 0) {
                            hql.append(" order by ").append(orderBys[0]);
                            int length = orderBys.length;
                            for (int i = 1; i < length; i++) {
                                // 添加排序字段
                                hql.append(",").append(orderBys[i]);
                            }
                            // 添加排序类型
                            if (isAsc) {
                                hql.append(" asc");
                            }
                            else {
                                hql.append(" desc");
                            }
                        }
                        String[] values = new String[valueList.size()];
                        values = valueList.toArray(values);

                        Query queryObject = session.createQuery(hql.toString());
                        queryObject.setCacheable(true);
                        if (values != null) {
                            for (int i = 0; i < values.length; i++) {

                                queryObject.setParameter(i, values[i]);
                            }
                        }
                        int startIndex = getStartOfPage(pageNo, pageSize);
                        return queryObject.setFirstResult(startIndex)
                                .setMaxResults(pageSize).list();
                    }
                });//, true);
    }

    protected String formHibernateExpression2Hql(
            HibernateExpression expression, List<String> valueList,
            LogicalType logicalType) {
        StringBuilder hql = new StringBuilder();
        // 判断查询条件类型
        if (expression instanceof BetweenExpression) {
            // Between
            BetweenExpression de = (BetweenExpression) expression;
            hql.append(" ").append(de.getPropertyName()).append(
                    " between (?,?)");
            // 添加查询值
            valueList.add(de.getLo().toString());
            valueList.add(de.getHi().toString());
            // ====== Between end .
        }
        else if (expression instanceof CompareExpression) {
            // Compare
            CompareExpression de = (CompareExpression) expression;
            hql.append(" ").append(de.getPropertyName()).append(
                    de.getCompareType().getValue()).append("?");
            // 添加查询值
            valueList.add(de.getValue().toString());
            // ====== Compare end .
        }
        else if (expression instanceof InExpression) {
            // In
            InExpression de = (InExpression) expression;
            hql.append(" ").append(de.getPropertyName()).append(" in (");
            int length = de.getValues().length;
            // 添加查询值
            hql.append("?");
            valueList.add(de.getValues()[0].toString());
            for (int i = 1; i < length; i++) {
                hql.append(",?");
                // 添加查询值
                valueList.add(de.getValues()[i].toString());
            }
            hql.append(")");
            // ====== In end .
        }
        else if (expression instanceof NotNullExpression) {
            // NotNull
            NotNullExpression de = (NotNullExpression) expression;
            hql.append(" ").append(de.toString());
            // ====== NotNull end .
        }
        else if (expression instanceof NullExpression) {
            // Null
            NullExpression de = (NullExpression) expression;
            hql.append(" ").append(de.toString());
            // ====== Null end .
        }
        else if (expression instanceof LogicalExpression) {
            // Logical
            LogicalExpression de = (LogicalExpression) expression;
            hql.append(" (");
            hql
                    .append(formHibernateExpression2Hql(de.getLhs(), valueList,
                            null));
            hql.append(" ").append(de.getType()).append(" ").append(
                    formHibernateExpression2Hql(de.getRhs(), valueList, null));
            hql.append(")");
            // ====== Logical end .
        }

        if (logicalType != null) {
            hql.append(" ").append(logicalType.getValue());
        }
        return hql.toString();
    }

    private static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }

    public Integer executeUpdate(final String hql, final Object... objects) {
        return getHibernateGenericController().executeUpdate(hql, objects);
    }

    /**
     * <p>@Title:		getAllObjects</p>
     * <p>@Description:	获取所有对象</p>
     * @date:			2018-1-17
     * @author:			lxc
     */
    protected <T> ResultFilter<T> getAllObjects(Class<T> clazz,
                                                Collection<HibernateExpression> expressions) {
        return getAllObjects(clazz, expressions, false, "id");
    }

    /**
     * <p>@Title:		getAllObjects</p>
     * <p>@Description:	获取所有对象</p>
     * @date:			2018-1-17
     * @author:			lxc
     */
    @SuppressWarnings("unchecked")
    protected <T> ResultFilter<T> getAllObjects(Class<T> clazz,
                                                Collection<HibernateExpression> expressions, boolean isAsc, String... orderBys) {

        List<T> items = new ArrayList<T>();
        items = getHibernateGenericController().findBy(clazz,
                1, Integer.MAX_VALUE,
                expressions, isAsc, orderBys);

        int totalCount = items == null ? 0 : items.size();

        ResultFilter<T> resultFilter = new ResultFilter<T>(totalCount,
                Integer.MAX_VALUE, 1);
        resultFilter.setItems(items);
        return resultFilter;
    }

    /**
     *
     * <p>@Title:		getSingleObject</p>
     * <p>@Description:	获取单个对象</p>
     * @date:			2018-1-17
     * @author:			lxc
     */
    @SuppressWarnings("unchecked")
    protected <T> ResultFilter<T> getSingleObject(Class<T> clazz,
                                                  Collection<HibernateExpression> expressions, int pageSize,
                                                  int currentPage, boolean isAsc, String... orderBys) {
        List<T> items = new ArrayList<T>();

        items = getHibernateGenericController().findBy(clazz,
                currentPage, pageSize,
                expressions, isAsc, orderBys);
        ResultFilter<T> resultFilter = new ResultFilter<T>(items.size(), pageSize, currentPage);
        resultFilter.setItems(items);
        return resultFilter;
    }
}
