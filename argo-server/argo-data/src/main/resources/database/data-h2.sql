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