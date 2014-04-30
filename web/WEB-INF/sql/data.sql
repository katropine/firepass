INSERT INTO `user` (`id`, `firstname`, `lastname`, `email`, `password`, `created`, `usergroup_id`) VALUES
(1, 'kriss', 'kross', 'kriss@test.com', '$2a$10$uozHooPEvjzY6.yp/YePKOJPNnHDPebZvvRpFE6O/W3aUX3tRefla', '2014-04-25 21:24:50', 1);

INSERT INTO `access_control_list` (`id`, `permission`, `usergroup_id`, `can_view`, `can_insert`, `can_update`, `can_delete`) VALUES
(1, 'USER', 1, 1, 1, 1, 1),
(2, 'USER_GROUP', 1, 1, 1, 1, 1),
(3, 'RESOURCE', 1, 1, 1, 1, 1),
(4, 'RESOURCE_GROUP', 1, 1, 1, 1, 1),
(5, 'USER', 2, 1, 1, 0, 0),
(6, 'USER_GROUP', 2, 1, 0, 0, 0),
(7, 'RESOURCE', 2, 1, 1, 1, 1),
(8, 'RESOURCE_GROUP', 2, 1, 1, 1, 1),
(9, 'USER', 3, 0, 0, 0, 0),
(10, 'USER_GROUP', 3, 0, 0, 0, 0),
(11, 'RESOURCE', 3, 1, 1, 1, 0),
(12, 'RESOURCE_GROUP', 3, 1, 0, 0, 0);

INSERT INTO `user_group` (`id`, `name`) VALUES
(1, 'Super Administrator'),
(2, 'Administrator'),
(3, 'User');
