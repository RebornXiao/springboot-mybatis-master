package cn.no7player.service.impl;

import cn.no7player.mapper.BaseMapper;
import cn.no7player.mapper.UserMapper;
import cn.no7player.model.Page;
import cn.no7player.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;

import java.lang.reflect.ParameterizedType;

public class BaseService<T> implements IBaseService<T> {

    @Autowired
    BaseMapper<T> baseMapper;

    @Autowired
    UserMapper userMapper;

    @PostConstruct//在构造方法后，初化前执行
    private void initBaseMapper() throws Exception{
        //完成以下逻辑，需要对研发本身进行命名与使用规范
        //this关键字指对象本身，这里指的是调用此方法的实现类（子类）
        System.out.println("=======this :"+this);
        System.out.println("=======父类基本信息："+this.getClass().getSuperclass());
        System.out.println("=======父类和泛型的信息："+this.getClass().getGenericSuperclass());

        ParameterizedType type =(ParameterizedType) this.getClass().getGenericSuperclass();
        //获取第一个参数的class
        Class clazz = (Class)type.getActualTypeArguments()[0];
        System.out.println("=======class:"+clazz);
        //转化为属性名（相关的Mapper子类的引用名）Supplier  supplierMapper
        String localField = clazz.getSimpleName().substring(0,1).toLowerCase()+clazz.getSimpleName().substring(1)+"Mapper";
        System.out.println("=======localField:"+localField);
        //getDeclaredField:可以使用于包括私有、默认、受保护、公共字段，但不包括继承的字段
        Field field=this.getClass().getSuperclass().getDeclaredField(localField);
        System.out.println("=======field:"+field);
        System.out.println("=======field对应的对象:"+field.get(this));
        Field baseField = this.getClass().getSuperclass().getDeclaredField("baseMapper");

        System.out.println("=======baseField:"+baseField);
        System.out.println("=======baseField对应的对象:"+baseField.get(this));
        //field.get(this)获取当前this的field字段的值。例如：Supplier对象中，baseMapper所指向的对象为其子类型SupplierMapper对象，子类型对象已被spring实例化于容器中
        baseField.set(this, field.get(this));
        System.out.println("========baseField对应的对象:"+baseMapper);

    }

    @Override
    public int insert(T entity) throws Exception {
        return baseMapper.insert(entity);
    }

    @Override
    public int update(T entity) throws Exception {
        return baseMapper.update(entity);
    }

    @Override
    public int delete(T entity) throws Exception {
        return baseMapper.delete(entity);
    }

    @Override
    public int deleteList(String[] pks) throws Exception {
        return baseMapper.deleteList(pks);
    }

    @Override
    public T select(T entity) {
        return baseMapper.select(entity);
    }

    @Override
    public Page<T> selectPage(Page<T> page) {
        page.setList(baseMapper.selectPageList(page));
        page.setTotalRecord(baseMapper.selectPageCount(page));
        return page;
    }
}
