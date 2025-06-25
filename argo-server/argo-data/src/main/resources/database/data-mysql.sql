--
--
--
--
--


--
-- 新增用户信息
--

insert into ag_adm_user (username, password)
select 'admin', 'ea48576f30be1669971699c09ad05c94'
from dual
where not exists(select 1 from ag_adm_user where username = 'admin');



--
-- 新增应用信息
--

insert into ag_cms_app (app_code, app_name, icon, remark, operator_code)
select 'argo-front',
       'ARGO',
       'https://gips1.baidu.com/it/u=1363769061,2430442523&fm=3044',
       'ARGO服务',
       'admin'
from dual
where not exists(select 1 from ag_cms_app where app_code = 'argo-front');

insert into ag_cms_app (app_code, app_name, icon, remark, operator_code)
select 'liens-shop-center',
       '千原商城',
       'https://bpic.51yuansu.com/pic3/cover/03/44/02/5ba256823bb46_610.jpg?x-oss-process=image/resize,h_360,m_lfit/sharpen,1004',
       '千原商城APP',
       'admin'
from dual
where not exists(select 1 from ag_cms_app where app_code = 'argo-front');