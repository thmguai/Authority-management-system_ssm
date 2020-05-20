package com.it.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.it.ssm.dao.ISyslogDao;
import com.it.ssm.domain.Syslog;
import com.it.ssm.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SyslogServiceImpl implements ISyslogService{

    @Autowired
    private ISyslogDao syslogDao;

    @Override
    public void save(Syslog syslog) {
        syslogDao.save(syslog);
    }

    @Override
    public List<Syslog> findAll(Integer page, Integer size) {

        PageHelper.startPage(page,size);
        return syslogDao.findAll();
    }
}
