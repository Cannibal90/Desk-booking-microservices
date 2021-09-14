-- LABORATORY ROOM
INSERT INTO laboratory_room (floor, room_supervisor) VALUES (0, 'Nowak Jan');
INSERT INTO laboratory_room (floor, room_supervisor) VALUES (0, 'Kowalski Piotr');
INSERT INTO laboratory_room (floor, room_supervisor) VALUES (0, 'Nowak Jan');
INSERT INTO laboratory_room (floor, room_supervisor) VALUES (0, 'Kowalski Piotr');

INSERT INTO laboratory_room (floor, room_supervisor) VALUES (1, 'Piatek Marek');
INSERT INTO laboratory_room (floor, room_supervisor) VALUES (1, 'Dzienny Tomasz');
INSERT INTO laboratory_room (floor, room_supervisor) VALUES (1, 'Dzienny Tomasz');
INSERT INTO laboratory_room (floor, room_supervisor) VALUES (1, 'Piatek Marek');

-- DESKS
INSERT INTO desk (desk_type, lr_id) VALUES ('ADJUSTABLE_HEIGHT', 1);
INSERT INTO desk (desk_type, lr_id) VALUES ('NORMAL', 1);
INSERT INTO desk (desk_type, lr_id) VALUES ('ADJUSTABLE_HEIGHT', 1);

INSERT INTO desk (desk_type, lr_id) VALUES ('NORMAL', 2);
INSERT INTO desk (desk_type, lr_id) VALUES ('ADJUSTABLE_HEIGHT', 2);
INSERT INTO desk (desk_type, lr_id) VALUES ('NORMAL', 2);

INSERT INTO desk (desk_type, lr_id) VALUES ('ADJUSTABLE_HEIGHT', 3);
INSERT INTO desk (desk_type, lr_id) VALUES ('ADJUSTABLE_HEIGHT', 3);
INSERT INTO desk (desk_type, lr_id) VALUES ('ADJUSTABLE_HEIGHT', 3);

INSERT INTO desk (desk_type, lr_id) VALUES ('NORMAL', 4);
INSERT INTO desk (desk_type, lr_id) VALUES ('NORMAL', 4);
INSERT INTO desk (desk_type, lr_id) VALUES ('NORMAL', 4);

INSERT INTO desk (desk_type, lr_id) VALUES ('ADJUSTABLE_HEIGHT', 5);
INSERT INTO desk (desk_type, lr_id) VALUES ('NORMAL', 5);
INSERT INTO desk (desk_type, lr_id) VALUES ('ADJUSTABLE_HEIGHT', 5);

INSERT INTO desk (desk_type, lr_id) VALUES ('NORMAL', 6);
INSERT INTO desk (desk_type, lr_id) VALUES ('ADJUSTABLE_HEIGHT', 6);
INSERT INTO desk (desk_type, lr_id) VALUES ('NORMAL', 6);

INSERT INTO desk (desk_type, lr_id) VALUES ('ADJUSTABLE_HEIGHT', 7);
INSERT INTO desk (desk_type, lr_id) VALUES ('NORMAL', 7);
INSERT INTO desk (desk_type, lr_id) VALUES ('ADJUSTABLE_HEIGHT', 7);

INSERT INTO desk (desk_type, lr_id) VALUES ('NORMAL', 8);
INSERT INTO desk (desk_type, lr_id) VALUES ('ADJUSTABLE_HEIGHT', 8);
INSERT INTO desk (desk_type, lr_id) VALUES ('NORMAL', 8);

-- COMPUTER STATIONS
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10', 6, 1);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 1024, 'GeForce 2GB', true, true, 1, 'LAN', 'WINDOWS 10/LINUX', 8, 1);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i7', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 2);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'Integrated', true, true, 2, 'LAN', 'WINDOWS 10', 8, 3);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i3', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10/LINUX', 6, 3);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 4GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 4);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 1024, 'GeForce 1GB', true, true, 2, 'LAN', 'WINDOWS 10', 8, 4);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i7', 512, 'Integrated', true, true, 2, 'LAN', 'WINDOWS 10/LINUX', 6, 5);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'Integrated', true, true, 1, 'LAN', 'WINDOWS 10', 8, 6);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 1024, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10/LINUX', 8, 6);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i3', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 7);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 2GB', true, true, 2, 'LAN', 'WINDOWS 10', 8, 8);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i7', 1024, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10/LINUX', 8, 8);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 2GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 9);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10/LINUX', 8, 9);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'Integrated', true, true, 2, 'LAN', 'WINDOWS 10', 8, 10);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 11);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 1024, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 11);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i7', 512, 'Integrated', true, true, 2, 'LAN', 'WINDOWS 10/LINUX', 8, 12);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 13);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i7', 1024, 'Integrated', true, true, 2, 'LAN', 'WINDOWS 10', 8, 14);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 14);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10/LINUX', 8, 15);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 16);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i3', 512, 'GeForce 1GB', true, true, 2, 'LAN', 'WINDOWS 10/LINUX', 8, 16);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 1024, 'GeForce 2GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 17);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 18);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'Integrated', true, true, 2, 'LAN', 'WINDOWS 10/LINUX', 8, 18);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i7', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 19);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10/LINUX', 8, 19);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 1GB', true, true, 2, 'LAN', 'WINDOWS 10', 8, 20);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 1024, 'GeForce 3GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 20);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i3', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10/LINUX', 8, 21);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 1024, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 22);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 1GB', true, true, 2, 'LAN', 'WINDOWS 10', 8, 22);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 3GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 23);

INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i7', 1024, 'Integrated', true, true, 2, 'LAN', 'WINDOWS 10/LINUX', 8, 24);
INSERT INTO computer_station (cpu, drive, graphic_card, headphones, microphone, monitors, network_type, operating_system, ram, desk_id) VALUES ('Intel i5', 512, 'GeForce 1GB', true, true, 1, 'LAN', 'WINDOWS 10', 8, 24);



