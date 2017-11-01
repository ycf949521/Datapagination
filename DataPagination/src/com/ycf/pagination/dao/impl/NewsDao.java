package com.ycf.pagination.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ycf.pagination.NewsBean;
import com.ycf.pagination.PageBean;
import com.ycf.pagination.dao.INewsDao;
	/**
	 * 数据访问层接口实现
	 * @author 14325
	 *
	 */
public class NewsDao implements INewsDao {
	//获取数据源
	ComboPooledDataSource ds = new ComboPooledDataSource();
	//接口方法实现，获取指定页的数据库数据
	@Override
	public void getOnePage(PageBean<NewsBean> td) {
		//获取DButil的QueryRunner对象
		QueryRunner qr = new QueryRunner(ds);
		//构造sql字符串
		String sql = "select * from news limit ?,?";
		try {
			//查询一页数据并设置到PageBean中 
			//创建BeanListHandler
			//设置sql字符串参数
			td.setResaultSet(qr.query(sql,new BeanListHandler<NewsBean>(NewsBean.class),
										Integer.parseInt(td.getCurrentPage()),
										Integer.parseInt(td.getLineNumPerPage())
									)
							);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//接口方法实现，获取列表总数目，并设置到PageBean中
	@Override
	public int getTotleNum(PageBean<NewsBean> td) {
		QueryRunner qr = new QueryRunner(ds);
		Long totleNum = (long) 0;
		//构造sql字符串
		String sql = "select count(*) from news";
		try {
			//查询列表总数目并设置到PageBean中
			totleNum = qr.query(sql,new ScalarHandler<Long>());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		td.setWholeNums(totleNum.toString());
		return totleNum.intValue();
	}

}
