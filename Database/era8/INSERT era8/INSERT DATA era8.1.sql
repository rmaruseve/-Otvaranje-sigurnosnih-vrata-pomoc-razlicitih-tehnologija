INSERT INTO `ac_role` (`rol_id`,`rol_name`,`rol_company`) VALUES (1,'Administrator','Mobilisis');


INSERT INTO `ac_user` (`usr_id`,`usr_name`,`usr_surname`,`usr_email`,`usr_activity`,`usr_password`,`usr_crypted_password`,`usr_rol_id`) VALUES (1,'Nikola','Horvat','nhorvat@mobilisis.com',1,'1234567',NULL,1);

INSERT INTO `ac_trigger_type` (`trt_id`,`trt_name`) VALUES (1,'Sms');

INSERT INTO `ac_trigger` (`trg_usr_id`,`trg_trt_id`,`trg_value`,`trg_activity`) VALUES (1,1,'385911527964',1);

INSERT INTO `ac_object_type` (`obt_id`,`obt_name`,`obt_in`,`obt_out`) VALUES (1,'ramp',1,NULL);

INSERT INTO `ac_object` (`obj_id`,`obj_name`,`obj_open`,`obj_auto`,`obj_activity`,`obj_GPS`,`obj_action`,`obj_obt_type_id`) VALUES (1,'ramp north',NULL,1,1,'46.287225,16.320886','DIO_A',1);
INSERT INTO `ac_object` (`obj_id`,`obj_name`,`obj_open`,`obj_auto`,`obj_activity`,`obj_GPS`,`obj_action`,`obj_obt_type_id`) VALUES (2,'ramp west',NULL,1,1,'46.287225,16.320886','DIO_B',1);

INSERT INTO `ac_object_has_trigger_type` (`oht_trt_id`,`oht_obj_id`,`oht_activity`) VALUES (1,1,1);

INSERT INTO `ac_day` (`day_id`,`day_name`) VALUES (1,'Monday');
INSERT INTO `ac_day` (`day_id`,`day_name`) VALUES (2,'Tuesday');
INSERT INTO `ac_day` (`day_id`,`day_name`) VALUES (3,'Wednesday');
INSERT INTO `ac_day` (`day_id`,`day_name`) VALUES (4,'Thursday');
INSERT INTO `ac_day` (`day_id`,`day_name`) VALUES (5,'Friday');
INSERT INTO `ac_day` (`day_id`,`day_name`) VALUES (6,'Saturday');
INSERT INTO `ac_day` (`day_id`,`day_name`) VALUES (7,'Sunday');

INSERT INTO `ac_profil` (`pro_id`,`pro_name`,`pro_activity`) VALUES (1,'employee',1);

INSERT INTO `ac_schedule` (`sch_pro_id`,`sch_day_id`,`sch_time_from`,`sch_time_to`) VALUES (1,1,'07:00:00','17:00:00');
INSERT INTO `ac_schedule` (`sch_pro_id`,`sch_day_id`,`sch_time_from`,`sch_time_to`) VALUES (1,2,'07:00:00','17:00:00');
INSERT INTO `ac_schedule` (`sch_pro_id`,`sch_day_id`,`sch_time_from`,`sch_time_to`) VALUES (1,3,'07:00:00','17:00:00');
INSERT INTO `ac_schedule` (`sch_pro_id`,`sch_day_id`,`sch_time_from`,`sch_time_to`) VALUES (1,4,'07:00:00','17:00:00');
INSERT INTO `ac_schedule` (`sch_pro_id`,`sch_day_id`,`sch_time_from`,`sch_time_to`) VALUES (1,5,'07:00:00','17:00:00');
INSERT INTO `ac_schedule` (`sch_pro_id`,`sch_day_id`,`sch_time_from`,`sch_time_to`) VALUES (1,6,'07:00:00','14:00:00');

INSERT INTO `ac_access` (`acs_id`,`acs_valid_from`,`acs_valid_to`,`acs_opening_counter`,`acs_usr_id`,`acs_pro_id`,`acs_obj_id`) VALUES (1,'2018-10-15 00:00:00','9999-12-31 23:59:59',NULL,1,1,1);
