package com.accAyo.serverProjectDemo.framwork.hibernateDao.HibernateBaseService;

import com.accAyo.serverProjectDemo.util.BeanUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午9:41 2018/5/16
 */
public class HibernateGenericController extends HibernateDaoSupport {
    private boolean cacheQueries = false;

    public void setCacheQueries(boolean cacheQueries) {
        this.cacheQueries = cacheQueries;
    }

    public boolean isCacheQueries() {
        return cacheQueries;
    }

    protected Criteria createCriteria(Class clazz) {
        Criteria criteria = getSession().createCriteria(clazz);
        if (isCacheQueries()) {
            criteria.setCacheable(true);
        }
        return criteria;
    }
    protected DetachedCriteria createDetachedCriteria(Class clazz) {
        return DetachedCriteria.forClass(clazz);
    }
    protected <T> DetachedCriteria createDetachedCriteria(Class<T> tClass, Criterion... criterions) {
        DetachedCriteria criteria = createDetachedCriteria(tClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }
    protected <T> DetachedCriteria createDetachedCriteria(Class<T> clasz,
                                                          String orderBy, boolean isAsc, Criterion... criterions) {
        DetachedCriteria criteria = createDetachedCriteria(clasz, criterions);

        if (isAsc) {
            criteria.addOrder(Order.asc(orderBy));
        }
        else {
            criteria.addOrder(Order.desc(orderBy));
        }

        return criteria;
    }
    protected <T> DetachedCriteria createDetachedCriteria(Class<T> clasz,
                                                          boolean isAsc, String[] orderBys, Criterion... criterions) {
        DetachedCriteria criteria = createDetachedCriteria(clasz, criterions);
        if (orderBys != null) {
            for (String orderBy : orderBys) {
                if (isAsc) {
                    criteria.addOrder(Order.asc(orderBy));
                }
                else {
                    criteria.addOrder(Order.desc(orderBy));
                }
            }
        }
        return criteria;
    }
    protected <T> DetachedCriteria createDetachedCriteria(Class<T> clasz,
              Collection<HibernateExpression> expressions) {
        List<Criterion> criterions = new ArrayList<Criterion>();
        for (HibernateExpression expression : expressions) {
            Criterion criterion = expression.createCriteria();
            if (criterion != null) {
                criterions.add(criterion);
            }
        }
        return createDetachedCriteria(clasz, criterions
                .toArray(new Criterion[0]));
    }
    protected <T> DetachedCriteria createDetachedCriteria(Class<T> clasz,
              String orderBy, boolean isAsc,
              Collection<HibernateExpression> expressions) {
        List<Criterion> criterions = new ArrayList<Criterion>();
        for (HibernateExpression expression : expressions) {
            Criterion criterion = expression.createCriteria();
            if (criterion != null) {
                criterions.add(criterion);
            }
        }
        return createDetachedCriteria(clasz, orderBy, isAsc, criterions
                .toArray(new Criterion[0]));
    }
    protected <T> DetachedCriteria createDetachedCriteria(Class<T> clasz,
                                                          Collection<HibernateExpression> expressions, boolean isAsc,
                                                          String... orderBys) {
        List<Criterion> criterions = new ArrayList<Criterion>();
        for (HibernateExpression expression : expressions) {
            Criterion criterion = expression.createCriteria();
            if (criterion != null) {
                criterions.add(criterion);
            }
        }
        return createDetachedCriteria(clasz, isAsc, orderBys, criterions
                .toArray(new Criterion[0]));
    }
    protected <T> Criteria createCriteria(Class<T> clasz, String orderBy,
                                          boolean isAsc, Criterion... criterions) {
        Criteria criteria = createCriteria(clasz, criterions);
        if (isAsc) {
            criteria.addOrder(Order.asc(orderBy));
        }
        else {
            criteria.addOrder(Order.desc(orderBy));
        }
        return criteria;
    }
    protected <T> Criteria createCriteria(Class<T> clasz,
                                          Criterion... criterions) {
        Criteria criteria = createCriteria(clasz);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }
    protected <T> Criteria createCriteria(Class<T> clasz,
                                         Collection<HibernateExpression> expressions) {
        List<Criterion> criterions = new ArrayList<Criterion>();
        for (HibernateExpression expression : expressions) {
            Criterion criterion = expression.createCriteria();
            if (criterion != null) {
                criterions.add(criterion);
            }
        }
        return createCriteria(clasz, criterions.toArray(new Criterion[0]));
    }
    protected <T> Criteria createCriteria(Class<T> clasz, String orderBy,
                                          boolean isAsc, Collection<HibernateExpression> expressions) {
        List<Criterion> criterions = new ArrayList<Criterion>();
        for (HibernateExpression expression : expressions) {
            Criterion criterion = expression.createCriteria();
            if (criterion != null) {
                criterions.add(criterion);
            }
        }
        return createCriteria(clasz, orderBy, isAsc, criterions
                .toArray(new Criterion[0]));
    }

    public Session getSession() {
        return getHibernateTemplate().getSessionFactory().getCurrentSession();
    }

    protected Query createQuery(String hql) throws HibernateException {
        Query query = getSession().createQuery(hql);
        return query;
    }

    public <T> T load(Class<T> clazz, Serializable id) throws HibernateException{
        return (T) getHibernateTemplate().load(clazz, id);
    }

    public <T> T load(Class<T> clazz, Serializable id, LockMode lockMode) throws HibernateException {
        return (T) getHibernateTemplate().load(clazz, id, lockMode);
    }

    public <T> T get(Class<T> clazz, Serializable id) {
        return (T) getHibernateTemplate().get(clazz, id);
    }

    public <T> T get(Class<T> tClass, Serializable id, LockMode lockMode) {
        return (T) getHibernateTemplate().get(tClass, id, lockMode);
    }

    public <T> List<T> getAll(Class<T> tClass) {
        return getHibernateTemplate().loadAll(tClass);
    }

    public <T> List<?> getAll(Class<T> tClass, String orderBy, boolean isAsc) {
        if (isAsc) {
            return getHibernateTemplate().findByCriteria(
                    DetachedCriteria.forClass(tClass).addOrder(Order.asc(orderBy)));
        }else {
            return getHibernateTemplate().findByCriteria(
                    DetachedCriteria.forClass(tClass).addOrder(Order.desc(orderBy)));
        }
    }

    public void delete(Object entity) {
        getHibernateTemplate().delete(entity);
    }

    public void delete(Collection collection) {
        getHibernateTemplate().deleteAll(collection);
    }

    public <T> T merge(T entity) {
        return getHibernateTemplate().merge(entity);
    }

    public void update(Object entity) {
        getHibernateTemplate().update(entity);
    }

    public void save(Object entity) {
        getHibernateTemplate().save(entity);
    }

    public void flush() {
        getHibernateTemplate().flush();
    }

    public void clear() {
        getHibernateTemplate().clear();
    }

    public void refresh(Object entity, LockMode lockMode) {
        getHibernateTemplate().refresh(entity, lockMode);
    }

    public void refresh(Object entity) {
        getHibernateTemplate().refresh(entity);
    }

    public List findBy(String hql, Object... values) {
        return getHibernateTemplate().find(hql, values);
    }

    public Integer executeUpdate(final String hql, final Object... objects) {
        return (Integer) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                        throws HibernateException {
                            Query query = session.createQuery(hql);
                            if (objects != null) {
                                for (int i = 0; i < objects.length; i ++) {
                                    query.setParameter(i, objects[i]);
                                }
                            }
                            return query.executeUpdate();
                    }
                });
    }

    public <T> List<T> findBy(Class<T> tClass, String propertyName, Object value) {
        return (List<T>) getHibernateTemplate().findByCriteria(createDetachedCriteria(tClass, Restrictions.eq(propertyName, value)));
    }

    public <T> List<T> findby(Class<T> tClass, String propertyName, Object value,
                              String orderBy, boolean isAsc) {
        return (List<T>) getHibernateTemplate().findByCriteria(
                createDetachedCriteria(tClass, orderBy, isAsc, Restrictions.eq(propertyName, value)));
    }

    public <T> T findUniqueBy(final Class<T> tClass, final String propertyName,
                              final Object value) {
        return (T) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException{
                Criteria criteria = session.createCriteria(tClass);
                criteria.add(Restrictions.eq(propertyName, value));
                return criteria.uniqueResult();
            }
        });
    }

    public Long getResultCount(String hql, Object... values) {
        Assert.hasText(hql);
        String countQueryString = "select count(*) " + removeSelect(removeOrders(hql));
        List countList = getHibernateTemplate().find(countQueryString, values);
        if (countList.size() == 0) {
            return 0l;
        } else {
            return Long.valueOf(countList.size());
        }
    }

    public Long getResultCount(Criteria criteria) {
        Assert.notNull(criteria);
        CriteriaImpl impl = (CriteriaImpl) criteria;
        Long totalCount  = 0L;
        try {
            Projection projection = impl.getProjection();
            List<CriteriaImpl.OrderEntry> orderEntries;
            try {
                orderEntries = (List) BeanUtil.forceGetProperty(impl, "orderEntries");
                BeanUtil.forceSetProperty(impl, "orderEntries", new ArrayList());
            } catch (Exception e) {
                throw new InternalError("Runtime Exception impossibility throw ");
            }
            totalCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
            criteria.setProjection(projection);
            if (projection == null) {
                criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
            }

            try {
                BeanUtil.forceSetProperty(impl, "orderEntries", orderEntries);
            } catch (Exception e) {
                throw new InternalError("Runtime Exception impossibility throw ");
            }
        } catch (Exception e) {
        } finally {
            SessionFactoryUtils.closeSession((Session) impl.getSession());
        }

        return totalCount;
    }

    public Long getResultCount(Class tClass, Criterion... criterions) {
        Criteria criteria = createCriteria(tClass, criterions);
        return getResultCount(criteria);
    }

    public Long getResultCount(Class tClass, Collection<HibernateExpression> expressions) {
        Criteria criteria = createCriteria(tClass);
        for (HibernateExpression expression : expressions) {
            Criterion criterion = expression.createCriteria();
            if (criterion != null) {
                criteria.add(criterion);
            }
        }
        return getResultCount(criteria);
    }

    public List findBy(DetachedCriteria criteria, int pageNo, int pageSize) {
        Assert.notNull(criteria);
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
        int startIndex = getStartOfPage(pageNo, pageSize);
        return getHibernateTemplate().findByCriteria(criteria, startIndex,
                pageSize);
        // return criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
    }

    public List findBy(final  String hql, final int pageNo, final int pageSize, final Object... values) {
        Assert.hasText(hql);
        Assert.isTrue(pageNo >= 1, "page should start from 1");
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) {
                Query queryObject = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < values.length; i ++) {
                        queryObject.setParameter(i, values[i]);
                    }
                }
                int startIndex = getStartOfPage(pageNo, pageSize);
                return queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
            }
        });
    }

    public List findBy(Class tClass, int pageNo, int pageSize, String orderBy, boolean isAsc,
                       Collection<HibernateExpression> expressions) {
        DetachedCriteria criteria = createDetachedCriteria(tClass, orderBy, isAsc, expressions);
        return findBy(criteria, pageNo, pageSize);
    }

    public List findBy(Class tClass, int pageNo, int pageSize, Collection<HibernateExpression> expressions,
                       boolean isAsc, String orderBys) {
        DetachedCriteria criteria = createDetachedCriteria(tClass, expressions, isAsc, orderBys);
        return findBy(criteria, pageNo, pageSize);
    }

    public List findBy(Class tClass, int pageNo, int pageSize, String orderBy,
                       boolean isAsc, Criterion... criterions) {
        DetachedCriteria criteria = createDetachedCriteria(tClass, orderBy, isAsc, criterions);
        return findBy(criteria, pageNo, pageSize);
    }

    private static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }

    private static Long toLong(Object object) {
        if (object instanceof Long) {
            return (Long) object;
        }
        if (object instanceof Integer) {
            Integer i = (Integer) object;
            return i.longValue();
        }
        return 0L;
    }

    public List findByWithStart(DetachedCriteria criteria, int startIndex,
                                int rowSize) {
        Assert.notNull(criteria);
        Assert.isTrue(startIndex >= 0, "startIndex should start from 0");
        return getHibernateTemplate().findByCriteria(criteria, startIndex,
                rowSize);
        // criteria.setFirstResult(startIndex).setMaxResults(rowSize).list();
    }

    public List findByWithStart(Class clasz, int startIndex, int rowSize,
                                Collection<HibernateExpression> expressions) {
        DetachedCriteria criteria = createDetachedCriteria(clasz, expressions);
        return findByWithStart(criteria, startIndex, rowSize);
    }

    public List findByWithStart(Class clasz,
                                Collection<HibernateExpression> expressions, int startIndex,
                                int rowSize, boolean isAsc, String... orderBys) {
        DetachedCriteria criteria = createDetachedCriteria(clasz, expressions,
                isAsc, orderBys);
        return findByWithStart(criteria, startIndex, rowSize);
    }

    public List findBy(Class clasz, int pageNo, int pageSize,
                       Collection<HibernateExpression> expressions, boolean isAsc,
                       String... orderBys) {
        DetachedCriteria criteria = createDetachedCriteria(clasz, expressions,
                isAsc, orderBys);
        return findBy(criteria, pageNo, pageSize);
    }

    public <T> boolean isUnique(Class<T> clasz, Object entity,
                                String uniquePropertyNames) {
        Assert.hasText(uniquePropertyNames);
        DetachedCriteria criteria = createDetachedCriteria(clasz)
                .setProjection(Projections.rowCount());
        String[] nameList = uniquePropertyNames.split(",");
        try {
            for (String name : nameList) {
                criteria.add(Restrictions.eq(name, PropertyUtils.getProperty(
                        entity, name)));
            }
            Serializable id = getId(clasz, entity);
            if (id != null) {
                String idName = getIdName(clasz);
                criteria.add(Restrictions.not(Restrictions.eq(idName, id)));
            }

        }
        catch (Exception e) {
            ReflectionUtils.handleReflectionException(e);
        }
        Integer resultCount = getUniqueNum(criteria);
        // Integer resultCount = (Integer)criteria.uniqueResult();
        return resultCount.intValue() == 0;
    }

    private Integer getUniqueNum(final DetachedCriteria criteria) {
        return (Integer) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        Criteria cri = criteria.getExecutableCriteria(session);
                        return cri.uniqueResult();
                    }
                });//, true);
    }

    public Serializable getId(Class clasz, Object entity)
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {
        Assert.notNull(entity);
        Assert.notNull(clasz);

        return (Serializable) PropertyUtils.getProperty(entity,
                getIdName(clasz));
    }

    public Serializable getId(Object entity) {
        return getSession().getIdentifier(entity);
    }

    public String getIdName(Class clazz) {
        Assert.notNull(clazz);
        ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
        Assert.notNull(meta, "Class " + clazz
                + " not define in hibernate session factory.");
        String idName = meta.getIdentifierPropertyName();
        Assert.hasText(idName, clazz.getSimpleName()
                + " has no identifier property define.");
        return idName;
    }

    public void evict(final Object object) {
        getHibernateTemplate().evict(object);
    }

    public void evict(final Class clasz, final Serializable id) {
        evict(clasz);
    }

    public void evict(final Class clasz) {
        getHibernateTemplate().getSessionFactory().getCurrentSession().evict(clasz);
    }


    private static String removeOrders(String hql) {
        Assert.hasText(hql);
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
                Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
    private static String removeSelect(String hql) {
        Assert.hasText(hql);
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1, " hql : " + hql
                + " must has a keyword 'from'");
        return hql.substring(beginPos);
    }
}
