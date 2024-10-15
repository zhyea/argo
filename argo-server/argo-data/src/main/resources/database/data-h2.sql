--
--
--
--
--


--
-- 新增用户信息
--

insert into ag_adm_user (username, password)
select 'admin', 'ea6aa923463acfa94b3aaa37231ee926'
from dual
where not exists(select 1 from ag_adm_user where username = 'admin');