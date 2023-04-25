delete from m_agreement_rule;


insert into m_agreement_rule
(rule_id,
 vehicle_model_code,
 vehicle_edition_code,
 start_time,
 end_time,
 expression,
 agrt_tpl_id,
 agrt_tpl_link,
 agrt_type,
 agrt_name,
 sign_type,
 exists_filler,
 create_time,
 update_time)
values (10000000000001,
        'AA',
        'AA003',
        '2023-03-21 00:00:00',
        '2024-03-21 00:00:00',
        "createTime >= 1679328000000 && createTime < 1710950400000 && vehicleEditionCode == 'AA003'",
        '',
        'https://static.jiduapp.cn/optimus/user-upload/1f91ed77f0.pdf',
        1,
        '集度汽车预订意向书',
        1,
        0,
        now(),
        now()),
       (10000000000002,
        'AA',
        'AA002',
        '2023-03-21 00:00:00',
        '2024-03-21 00:00:00',
        "createTime >= 1679328000000 && createTime < 1710950400000 && vehicleEditionCode == 'AA002'",
        '',
        'https://static.jiduapp.cn/optimus/user-upload/1f91ed77f0.pdf',
        1,
        '集度汽车预订意向书',
        1,
        0,
        now(),
        now()),
       (10000000000003,
        'AA',
        'AA001',
        '2023-03-21 00:00:00',
        '2024-03-21 00:00:00',
        "createTime >= 1679328000000 && createTime < 1710950400000 && vehicleEditionCode == 'AA001'",
        '',
        'https://static.jiduapp.cn/optimus/user-upload/1f91ed77f0.pdf',
        1,
        '集度汽车预订意向书',
        1,
        0,
        now(),
        now());



delete from m_deposit_rule;

insert into m_deposit_rule
(rule_id,
 vehicle_model_code,
 vehicle_edition_code,
 start_time,
 end_time,
 expression,
 fund_type,
 stage,
 amount,
 order_time_out,
 rule_desc,
 create_time,
 update_time)
values (2110000000000001,
        'AA',
        'AA003',
        '2023-01-01 00:00:00',
        '2024-03-21 00:00:00',
        "createTime >= 1672502400000 && createTime < 1710950400000 && vehicleEditionCode == 'AA003'",
        1,
        2,
        100,
        2880,
        'AA003小订规则',
        now(),
        now()),
       (2110000000000002,
        'AA',
        'AA002',
        '2023-03-21 00:00:00',
        '2024-03-21 00:00:00',
        "createTime >= 1679328000000 && createTime < 1710950400000 && vehicleEditionCode == 'AA002'",
        1,
        2,
        100,
        -1,
        'AA002小订规则',
        now(),
        now()),
       (2110000000000003,
        'AA',
        'AA001',
        '2023-03-21 00:00:00',
        '2024-03-21 00:00:00',
        "createTime >= 1679328000000 && createTime < 1710950400000 && vehicleEditionCode == 'AA001'",
        1,
        2,
        100,
        -1,
        'AA001小订规则',
        now(),
        now());


update m_deposit_rule set instruction = "1.预订金可退，详见《汽车预订意向书》；\n2.预计2023年下半年开启交付，具体时间见集度汽车后续官方公布；\n3.车辆可享受的新能源补贴，以届时适用的政策为准。";



delete from m_equity_rule;

insert into m_equity_rule
(rule_id,
 vehicle_model_code,
 vehicle_edition_code,
 start_time,
 end_time,
 expression,
 stage,
 equity_id,
 create_time,
 update_time)
values (3310000000000001,
        'AA',
        'AA003',
        '2023-01-01 00:00:00',
        '2024-03-21 00:00:00',
        "createTime >= 1672502400000 && createTime < 1710950400000 && vehicleEditionCode == 'AA003'",
        2,
        59321616728257,
        now(),
        now()),
       (3310000000000002,
        'AA',
        'AA002',
        '2023-03-21 00:00:00',
        '2024-03-21 00:00:00',
        "createTime >= 1679328000000 && createTime < 1710950400000 && vehicleEditionCode == 'AA002'",
        2,
        59321616728258,
        now(),
        now()),
       (3310000000000003,
        'AA',
        'AA001',
        '2023-03-21 00:00:00',
        '2024-03-21 00:00:00',
        "createTime >= 1679328000000 && createTime < 1710950400000 && vehicleEditionCode == 'AA001'",
        2,
        59321616728258,
        now(),
        now());