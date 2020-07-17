INSERT INTO user_profile (username, email, password, is_email_authenticated, is_ubi_authenticated)
values
('admin', 'test@email.com', 'test_password', TRUE, FALSE),
('user1', 'test@email.com', 'test_password', TRUE, FALSE),
('user2', 'test@email.com', 'test_password', TRUE, FALSE);


INSERT INTO POST (user_profile_id, notice, type, title, content, view_cnt, recommend_cnt, created_time)
values
(1, true, 'notice', '공지입니다', '공지입니다', 2, 0, '2020-01-01'),
(2, false, 'free', 'title1', 'conetne1', 0, 0, '2020-01-01'),
(2, false, 'free', 'title2', 'conetne2', 0, 0, '2020-01-01'),
(2, false, 'free', 'title3', 'conetne3', 0, 0, '2020-01-01'),
(2, false, 'free', 'title4', 'conetne4', 0, 0, '2020-01-01');

INSERT INTO COMMENT (user_profile_id, post_id, content, parent_comment_id, created_time)
values
(3, 2, '댓글입니다', null, '2020-01-02'),
(2, 2, '대댓글입니다', 1, '2020-01-03'),
(3, 2, '대대ㅡㅅ글입니다', 1, '2020-01-04'),
(2, 2, '새로운 댓글입니다', null, '2020-01-01');

INSERT INTO operator_description (name, name_oasis_id, ctu_oasis_id, operator_index, category, statistic_pvp_id, statistic_pve_id, statistic_pvp_oasis_id, statistic_pve_oasis_id)
values ('caveira', 207671, 207757, '3:8', 'def', 'operatorpvp_caveira_interrogations:1:8', 'operatorpve_caveira_interrogations:1:8', 207945, 207952),
  ('smoke', 62253, 62015, '2:1', 'def','operatorpvp_smoke_poisongaskill:2:1', 'operatorpve_smoke_poisongaskill:2:1', 194660, 194660),
  ('mute', 62252, 62015, '3:1', 'def','operatorpvp_mute_gadgetjammed:3:1', 'operatorpve_mute_gadgetjammed:3:1', 194664, 194664),
  ('sledge', 62245, 62015, '4:1', 'atk','operatorpvp_sledge_hammerhole:4:1', 'operatorpve_sledge_hammerhole:4:1', 194672, 194672),
  ('thatcher', 65143, 62015, '5:1', 'atk','operatorpvp_thatcher_gadgetdestroywithemp:5:1', 'operatorpve_thatcher_gadgetdestroywithemp:5:1', 194673, 194673),
  ('castle', 62248, 62183, '2:2', 'def','operatorpvp_castle_kevlarbarricadedeployed:2:2', 'operatorpve_castle_kevlarbarricadedeployed:2:2', 194663, 194663),
  ('ash', 62246, 62183, '3:2', 'atk','operatorpvp_ash_bonfirewallbreached:3:2', 'operatorpve_ash_bonfirewallbreached:3:2', 194677, 194677),
  ('pulse', 62250, 62183, '4:2', 'def','operatorpvp_pulse_heartbeatspot:4:2', 'operatorpve_pulse_heartbeatspot:4:2', 194665, 194665),
  ('thermite', 62247, 62183, '5:2', 'atk','operatorpvp_thermite_reinforcementbreached:5:2', 'operatorpve_thermite_reinforcementbreached:5:2', 194681, 194681),
  ('doc', 62251, 62016, '2:3', 'def','operatorpvp_doc_teammaterevive:2:3', 'operatorpve_doc_teammaterevive:2:3', 194666, 194666),
  ('rook', 62249, 62016, '3:3', 'def','operatorpvp_rook_armortakenteammate:3:3', 'operatorpve_rook_armortakenteammate:3:3', 194667, 194667),
  ('twitch', 65156, 62016, '4:3', 'atk','operatorpvp_twitch_gadgetdestroybyshockdrone:4:3', 'operatorpve_twitch_gadgetdestroybyshockdrone:4:3', 194686, 194686),
  ('montagne', 65159, 62016, '5:3', 'atk','operatorpvp_montagne_shieldblockdamage:5:3', 'operatorpve_montagne_shieldblockdamage:5:3', 194688, 194688),
  ('glaz', 62242, 62185, '2:4', 'atk','operatorpvp_glaz_sniperkill:2:4', 'operatorpve_glaz_sniperkill:2:4', 194689, 194689),
  ('fuze', 65168, 62185, '3:4', 'atk','operatorpvp_fuze_clusterchargekill:3:4', 'operatorpve_fuze_clusterchargekill:3:4', 194691, 194691),
  ('kapkan', 65171, 62185, '4:4', 'def','operatorpvp_kapkan_boobytrapkill:4:4', 'operatorpve_kapkan_boobytrapkill:4:4', 194668, 194668),
  ('tachanka', 65174, 62185, '5:4', 'def','operatorpvp_tachanka_turretkill:5:4', 'operatorpve_tachanka_turretkill:5:4', 194669, 194669),
  ('blitz', 62243, 62184, '2:5', 'atk','operatorpvp_blitz_flashedenemy:2:5', 'operatorpve_blitz_flashedenemy:2:5', 195161, 195161),
  ('iq', 113052, 62184, '3:5', 'atk','operatorpvp_iq_gadgetspotbyef:3:5', 'operatorpve_iq_gadgetspotbyef:3:5', 194696, 194696),
  ('jager', 113049, 62184, '4:5', 'def','operatorpvp_jager_gadgetdestroybycatcher:4:5', 'operatorpve_jager_gadgetdestroybycatcher:4:5', 194670, 194670),
  ('bandit', 65165, 62184, '5:5', 'def','operatorpvp_bandit_batterykill:5:5', 'operatorpve_bandit_batterykill:5:5', 194671, 194671),
  ('buck', 62244, 192111, '2:6', 'atk','operatorpvp_buck_kill:2:6', 'operatorpve_buck_kill:2:6', 200102, 200102),
  ('frost', 186159, 192111, '3:6', 'def','operatorpvp_frost_dbno:3:6', 'operatorpve_frost_beartrap_kill:3:6', 200104, 200103),
  ('blackbeard', 187249, 62186, '2:7', 'atk','operatorpvp_blackbeard_gunshieldblockdamage:2:7', 'operatorpve_blackbeard_gunshieldblockdamage:2:7', 202310, 202310),
  ('valkyrie', 187252, 62186, '3:7', 'def','operatorpvp_valkyrie_camdeployed:3:7', 'operatorpve_valkyrie_camdeployed:3:7', 202311, 202311),
  ('capitao', 207674, 207757, '2:8', 'atk','operatorpvp_capitao_lethaldartkills:2:8', 'operatorpve_capitao_lethaldartkills:2:8', 207946, 207946),
  ('hibana', 209183, 210241, '2:9', 'atk','operatorpvp_hibana_detonate_projectile:2:9', 'operatorpve_hibana_detonate_projectile:2:9', 217323, 217323),
  ('echo', 209180, 210241, '3:9', 'def','operatorpvp_echo_enemy_sonicburst_affected:3:9', 'operatorpve_echo_enemy_sonicburst_affected:3:9', 217324, 217324),
  ('jackal', 222182, 217493, '2:A', 'atk','operatorpvp_cazador_assist_kill:2:A', 'operatorpve_cazador_assist_kill:2:A', 229326, 229327),
  ('mira', 222172, 217493, '3:A', 'def','operatorpvp_black_mirror_gadget_deployed:3:A', 'operatorpve_black_mirror_gadget_deployed:3:A', 229328, 229329),
  ('ying', 239334, 231289, '2:B', 'atk','operatorpvp_dazzler_gadget_detonate:2:B', 'operatorpve_dazzler_gadget_detonate:2:B', 243475, 243475),
  ('lesion', 239337, 231289, '3:B', 'def','operatorpvp_caltrop_enemy_affected:3:B', 'operatorpve_caltrop_enemy_affected:3:B', 243474, 243474),
  ('ela', 254595, 254591, '2:C', 'def','operatorpvp_concussionmine_detonate:2:C', 'operatorpve_concussionmine_detonate:2:C', 254989, 254989),
  ('zofia', 254606, 254591, '3:C', 'atk','operatorpvp_concussiongrenade_detonate:3:C', 'operatorpve_concussiongrenade_detonate:3:C', 254990, 254990),
  ('dokkaebi', 273652, 273753, '2:D', 'atk','operatorpvp_phoneshacked:2:D', 'operatorpve_phoneshacked:2:D', 283005, 283005),
  ('vigil', 273663, 273753, '3:D', 'def','operatorpvp_attackerdrone_diminishedrealitymode:3:D', 'operatorpve_attackerdrone_diminishedrealitymode:3:D', 283004, 283004),
  ('lion', 281463, 282958, '3:E', 'atk','operatorpvp_tagger_tagdevice_spot:3:E', 'operatorpve_tagger_tagdevice_spot:3:E', 282608, 282608),
  ('finka', 281457, 282958, '4:E', 'atk','operatorpvp_rush_adrenalinerush:4:E', 'operatorpve_rush_adrenalinerush:4:E', 282609, 282609),
  ('maestro', 285888, 285859, '2:F', 'def','operatorpvp_barrage_killswithturret:2:F', 'operatorpve_barrage_killswithturret:2:F', 288335, 288335),
  ('alibi', 282399, 285859, '3:F', 'def','operatorpvp_deceiver_revealedattackers:3:F', 'operatorpve_deceiver_revealedattackers:3:F', 282404, 282403),
  ('maverick', 288504, 289336, '2:10', 'atk','operatorpvp_maverick_wallbreached:2:10', 'operatorpve_maverick_wallbreached:2:10', 293475, 293475),
  ('clash', 288526, 289336, '3:10', 'def','operatorpvp_clash_sloweddown:3:10', 'operatorpve_clash_sloweddown:3:10', 293483, 293483),
  ('nomad', 293435, 292860, '2:11', 'atk','operatorpvp_nomad_airjabdetonate:2:11', 'operatorpve_nomad_airjabdetonate:2:11', 293935, 293935),
  ('kaid', 293436, 292860, '3:11', 'def','operatorpvp_kaid_electroclawelectrify:3:11', 'operatorpve_kaid_electroclawelectrify:3:11', 294144, 294144),
  ('mozzie', 298616, 298584, '2:12', 'def','operatorpvp_mozzie_droneshacked:2:12', 'operatorpve_mozzie_droneshacked:2:12', 298664, 298664),
  ('gridlock', 298589, 298584, '3:12', 'atk','operatorpvp_gridlock_traxdeployed:3:12', 'operatorpve_gridlock_traxdeployed:3:12', 298667, 298667),
  ('nakk', 299657, 299652, '2:13', 'atk','operatorpvp_nokk_observationtooldeceived:2:13', '', 304013, 0),
  ('warden', 299668, 303929, '2:14', 'def','operatorpvp_warden_killswithglasses:2:14', 'operatorpve_warden_killswithglasses:2:14', 304018, 304018),
  ('goyo', 304196, 308458, '2:15', 'def','operatorpvp_goyo_volcandetonate:2:15', 'operatorpve_goyo_volcandetonate:2:15', 309864, 309864),
  ('amaru', 308469, 308460, '2:16', 'atk','operatorpvp_amaru_distancereeled:2:16', 'operatorpve_amaru_distancereeled:2:16', 309863, 309863),
  ('kali', 310757, 310754, '2:17', 'atk','operatorpvp_kali_gadgetdestroywithexplosivelance:2:17', 'operatorpve_kali_gadgetdestroywithexplosivelance:2:17', 312718, 312718),
  ('wamai', 310768, 310754, '3:17', 'def','operatorpvp_wamai_gadgetdestroybymagnet:3:17', 'operatorpve_wamai_gadgetdestroybymagnet:3:17', 312719, 312719),
  ('oryx', 317965, 318172, '2:18', 'def','operatorpvp_oryx_killsafterdash:2:18', 'operatorpve_oryx_killsafterdash:2:18', 318856, 318856),
  ('iana', 317976, 318171, '2:19', 'atk','operatorpvp_iana_killsafterreplicator:2:19', 'operatorpve_iana_killsafterreplicator:2:19', 318857, 318857);

-- INSERT INTO player (platform, player_id)
-- values ('uplay', 'piliot');

-- INSERT INTO casual_pvp (player_id, death, kills, match_lost, match_won, match_played, time_played, created_time )
-- values (1, 523, 510, 82, 100, 182, 160000, '2020-05-10 00:00:00'),
-- (1, 513, 497, 81, 97, 178, 159000, '2020-05-09 00:00:00'),
-- (1, 500, 490, 79, 86, 175, 158000, '2020-05-08 00:00:00');
--
--
-- INSERT INTO rank_pvp (player_id, kills, death, match_won, match_lost, match_played, time_played, created_time)
-- values
-- (1, 4733, 4714, 577, 543, 1120, 13432000, '2020-05-11 00:00:00'),
-- (1, 4729, 4709, 577, 542, 1119, 13431000, '2020-05-10 00:00:00'),
-- (1, 4715, 4700, 575, 541, 1116, 13430000, '2020-05-09 00:00:00'),
-- (1, 4710, 4695, 574, 541, 1115, 13429000, '2020-05-08 00:00:00');
--

