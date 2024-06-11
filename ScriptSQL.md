//-------------------------//

CREATE DATABASE bookingApp;
USE bookingApp;

//------------------------//

CREATE TABLE users(
id BIGINT auto_increment,
email VARCHAR(100) unique,
password VARCHAR(255),
fullname VARCHAR(100),
avatar VARCHAR(255),
address VARCHAR(255),
phone VARCHAR(15),
sex ENUM('MALE', 'FEMALE', 'OTHER'),
role_id INT,
is_deleted BOOLEAN DEFAULT FALSE,
PRIMARY KEY(id)
)
CREATE TABLE roles(
id INT auto_increment,
name VARCHAR(20) unique,
PRIMARY KEY(id)
)
CREATE TABLE hotels(
id INT auto_increment,
name VARCHAR(100),
avatar VARCHAR(600),
description TEXT,
user_id BIGINT,
phone VARCHAR(15),
is_deleted BOOLEAN DEFAULT FALSE,
open_time TIME(6),
close_time TIME(6),
checkin_time TIME(6),
checkout_time TIME(6),
rating DECIMAL(2,1),
PRIMARY KEY(id)
)

CREATE TABLE hotel_address(
hotel_id INT,
street_number INT,
street_name VARCHAR(100),
district VARCHAR(100),
city VARCHAR(100),
province VARCHAR(100),
country VARCHAR(100),
PRIMARY KEY(hotel_id)
)

CREATE TABLE hotel_image(
id INT auto_increment,
hotel_id INT,
image_title VARCHAR(255),
image_description VARCHAR(500),
image_path VARCHAR(600),
upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(id)
)

CREATE TABLE hotel_reviews(
id INT auto_increment,
hotel_id INT,
user_id BIGINT,
comment VARCHAR(500),
review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(id)
)

CREATE TABLE review_replies(
id INT auto_increment,
review_id INT,
user_id BIGINT,
reply_text VARCHAR(500),
reply_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(id)
)

CREATE TABLE amenities (
id INT auto_increment,
name VARCHAR(255),
icon VARCHAR(1900),
PRIMARY KEY(id)
)

CREATE TABLE hotel_amenities(
hotel_id INT,
amenity_id INT,
PRIMARY KEY(hotel_id, amenity_id)
)

CREATE TABLE room_amenities(

room_id INT,
amenity_id INT,
PRIMARY KEY(room_id, amenity_id)

);

CREATE TABLE rooms(
id INT auto_increment,
room_number INT,
description TEXT,
roomtype_id INT,
price DOUBLE,
hotel_id INT,
status ENUM('AVAILABLE','BOOKED','OCCUPIED','MAINTENANCE','CLEANING') DEFAULT 'AVAILABLE',
PRIMARY KEY(id)
)

CREATE TABLE room_image(
id INT auto_increment,
room_id INT,
image_title VARCHAR(255),
image_description VARCHAR(500),
image_path VARCHAR(600),
upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(id)
)

CREATE TABLE room_type(
id INT auto_increment,
name VARCHAR(100),
PRIMARY KEY(id)
)

CREATE TABLE booking(
id BIGINT auto_increment,
user_id BIGINT,
hotel_id INT,
booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
total_price DOUBLE,
payment_amount DOUBLE,
payment_status ENUM('TRANSFERRED','NOTTRANSFERRED') DEFAULT 'NOTTRANSFERRED',
payment_date TIMESTAMP,
PRIMARY KEY(id)
)

CREATE TABLE booking_room(
booking_id BIGINT,
room_id INT,
status ENUM('PENDING','CONFIRMED','CHECKIN','CHECKOUT','CANCELLED') DEFAULT 'PENDING',
checkin_date TIMESTAMP,
checkout_date TIMESTAMP,
PRIMARY KEY(booking_id,room_id)
);

//-------------------------------------------------------------------------------//

ALTER TABLE users ADD CONSTRAINT FK_role_id_users FOREIGN KEY(role_id) REFERENCES roles(id);
ALTER TABLE hotels ADD CONSTRAINT FK_user_id_hotels FOREIGN KEY(user_id) REFERENCES users(id);
ALTER TABLE hotel_address ADD CONSTRAINT FK_hotel_id_hotel_address FOREIGN KEY(hotel_id) REFERENCES hotels(id);
ALTER TABLE hotel_image ADD CONSTRAINT FK_hotel_id_hotel_image FOREIGN KEY(hotel_id) REFERENCES hotels(id);
ALTER TABLE hotel_reviews ADD CONSTRAINT FK_hotel_id_hotel_reviews FOREIGN KEY(hotel_id) REFERENCES hotels(id);
ALTER TABLE hotel_reviews ADD CONSTRAINT FK_user_id_hotel_reviews FOREIGN KEY(user_id) REFERENCES users(id);
ALTER TABLE review_replies ADD CONSTRAINT FK_review_id_review_replies FOREIGN KEY(review_id) REFERENCES hotel_reviews(id);
ALTER TABLE review_replies ADD CONSTRAINT FK_user_id_review_replies FOREIGN KEY(user_id) REFERENCES users(id);
ALTER TABLE hotel_amenities ADD CONSTRAINT FK_hotel_id_hotel_amenities FOREIGN KEY(hotel_id) REFERENCES hotels(id);
ALTER TABLE hotel_amenities ADD CONSTRAINT FK_amenity_id_hotel_amenities FOREIGN KEY(amenity_id) REFERENCES amenities(id);
ALTER TABLE room_amenities ADD CONSTRAINT FK_room_id_room_amenities FOREIGN KEY(room_id) REFERENCES rooms(id);
ALTER TABLE room_amenities ADD CONSTRAINT FK_amenity_id_room_amenities FOREIGN KEY(amenity_id) REFERENCES amenities(id);
ALTER TABLE rooms ADD CONSTRAINT FK_roomtype_id_rooms FOREIGN KEY(roomtype_id) REFERENCES room_type(id);
ALTER TABLE rooms ADD CONSTRAINT FK_hotel_id_rooms FOREIGN KEY(hotel_id) REFERENCES hotels(id);
ALTER TABLE room_image ADD CONSTRAINT FK_room_id_room_image FOREIGN KEY(room_id) REFERENCES rooms(id);
ALTER TABLE booking ADD CONSTRAINT FK_user_id_booking FOREIGN KEY(user_id) REFERENCES users(id);
ALTER TABLE booking ADD CONSTRAINT FK_hotel_id_booking FOREIGN KEY(hotel_id) REFERENCES hotels(id);
ALTER TABLE booking_room ADD CONSTRAINT FK_booking_id_booking_room FOREIGN KEY(booking_id) REFERENCES booking(id);
ALTER TABLE  booking_room ADD CONSTRAINT FK_room_id_booking_room FOREIGN KEY(room_id) REFERENCES rooms(id);




INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_HOTEL_OWNER');

//-------------INSERT USER-----------//


INSERT INTO users (email, password, fullname, avatar, address, phone, sex, role_id, is_deleted) VALUES
('user1@gmail.com', '$2a$12$3yKcwl8MEtwAuTm73e4Rve2gmYfnwWCAXhEVn7aMDa98D4dRY3QUW', 'User One', 'avatar1.jpg', 'Address 1', '1234567890', 'MALE', 1, FALSE),
('user2@gmail.com', '$2a$12$A8neWf3wsqRmVj/1wSaoGeNnOjOk8GiS4Xk2OjjX7OM6GFYBKGsJy', 'User Two', 'avatar2.jpg', 'Address 2', '1234567891', 'FEMALE', 2, FALSE),
('user3@gmail.com', '$2a$12$7PJLCqMgLUyZpFvI.QWLr.EyZAHGrqe3I.LOIrxzyum//AHLzc9Ve', 'User Three', 'avatar3.jpg', 'Address 3', '1234567892', 'OTHER', 2, FALSE),
('user4@gmail.com', '$2a$12$H.qOjLkO.91heJjKmpDV7OJjyU5BD9YFdQRzp9lqcTjYZektVOC7i', 'User Four', 'avatar4.jpg', 'Address 4', '1234567893', 'MALE', 2, FALSE),
('user5@gmail.com', '$2a$12$3h.4jB.cYKr48yrEx4bllOX/Y0ATeoHSmGo9ug39qppmfRxNRrRF6', 'User Five', 'avatar5.jpg', 'Address 5', '1234567894', 'FEMALE', 2, FALSE),
('user6@gmail.com', '$2a$12$uDkEOi4LEl.aANkb.Ifa6uJCVdZeGUqG4ylxNPmyvvMb30fJzCYN6', 'User Six', 'avatar6.jpg', 'Address 6', '1234567895', 'OTHER', 2, FALSE),
('user7@gmail.com', '$2a$12$dlM/rMGkVLzn.bQRgB/FgernpBj6.PC1zrBJzoYkj0ORF4cGPhHNm', 'User Seven', 'avatar7.jpg', 'Address 7', '1234567896', 'MALE', 3, FALSE),
('user8@gmail.com', '$2a$12$RbuGsYWi2oCcOft7wmVHFuB6tWZT5HptFypQ4/jv/uKFUPln5lZDu', 'User Eight', 'avatar8.jpg', 'Address 8', '1234567897', 'FEMALE', 3, FALSE),
('user9@gmail.com', '$2a$12$Qytg.9TZS0lUPRq7AWCcsu6X73/cqMF8G/v2SUwlJiqf1PuiyUPsm', 'User Nine', 'avatar9.jpg', 'Address 9', '1234567898', 'OTHER', 2, FALSE),
('user10@gmail.com', '$2a$12$0BjuqeYWgKWCyC7FIvgwdefLfc/d3ZeAUVZ/T/1ptxFeL1Qbj97BC', 'User Ten', 'avatar10.jpg', 'Address 10', '1234567899', 'MALE', 2, FALSE)

INSERT INTO users (email, password, fullname, avatar, address, phone, sex, role_id, is_deleted) VALUES
('user11@gmail.com', '$2a$12$3yKcwl8MEtwAuTm73e4Rve2gmYfnwWCAXhEVn7aMDa98D4dRY3QUW', 'Thien Bao', 'avatar11.jpg', 'Address 11', '123456788', 'MALE', 2, FALSE),
('user12@gmail.com', '$2a$12$A8neWf3wsqRmVj/1wSaoGeNnOjOk8GiS4Xk2OjjX7OM6GFYBKGsJy', 'Đăng', 'avatar12.jpg', 'Address 12', '123456781', 'FEMALE', 3, FALSE),
('user13@gmail.com', '$2a$12$7PJLCqMgLUyZpFvI.QWLr.EyZAHGrqe3I.LOIrxzyum//AHLzc9Ve', 'Suốt', 'avatar13.jpg', 'Address 13', '123456782', 'OTHER', 2, FALSE),
('user14@gmail.com', '$2a$12$H.qOjLkO.91heJjKmpDV7OJjyU5BD9YFdQRzp9lqcTjYZektVOC7i', 'Huy', 'avatar14.jpg', 'Address 14', '1234567883', 'MALE', 2, FALSE),
('user15@gmail.com', '$2a$12$3h.4jB.cYKr48yrEx4bllOX/Y0ATeoHSmGo9ug39qppmfRxNRrRF6', 'Tình', 'avatar15.jpg', 'Address 15', '1234567884', 'FEMALE', 2, FALSE),
('user16@gmail.com', '$2a$12$uDkEOi4LEl.aANkb.Ifa6uJCVdZeGUqG4ylxNPmyvvMb30fJzCYN6', 'Ngĩa', 'avatar16.jpg', 'Address 16', '1234567885', 'OTHER', 2, FALSE),
('user17@gmail.com', '$2a$12$dlM/rMGkVLzn.bQRgB/FgernpBj6.PC1zrBJzoYkj0ORF4cGPhHNm', 'Trang', 'avatar17.jpg', 'Address 17', '1234567886', 'MALE', 3, FALSE),
('user18@gmail.com', '$2a$12$RbuGsYWi2oCcOft7wmVHFuB6tWZT5HptFypQ4/jv/uKFUPln5lZDu', 'Tiến', 'avatar18.jpg', 'Address 18', '1234567887', 'FEMALE', 3, FALSE),
('user19@gmail.com', '$2a$12$Qytg.9TZS0lUPRq7AWCcsu6X73/cqMF8G/v2SUwlJiqf1PuiyUPsm', 'Thắm', 'avatar19.jpg', 'Address 19', '1234567888', 'OTHER', 3, FALSE),
('user20@gmail.com', '$2a$12$0BjuqeYWgKWCyC7FIvgwdefLfc/d3ZeAUVZ/T/1ptxFeL1Qbj97BC', 'Tùng', 'avatar20.jpg', 'Address 20', '1234567889', 'MALE', 3, FALSE);


INSERT INTO users (email, password, fullname, avatar, address, phone, sex, role_id, is_deleted) VALUES
('user21@gmail.com', '$2a$12$3yKcwl8MEtwAuTm73e4Rve2gmYfnwWCAXhEVn7aMDa98D4dRY3QUW', 'Văn A', 'avatar21.jpg', 'Address 21', '1234513271', 'MALE', 2, FALSE),
('user22@gmail.com', '$2a$12$A8neWf3wsqRmVj/1wSaoGeNnOjOk8GiS4Xk2OjjX7OM6GFYBKGsJy', 'Văn B', 'avatar22.jpg', 'Address 22', '123456732173', 'FEMALE', 3, FALSE),
('user23@gmail.com', '$2a$12$7PJLCqMgLUyZpFvI.QWLr.EyZAHGrqe3I.LOIrxzyum//AHLzc9Ve', 'Quốc C', 'avatar23.jpg', 'Address 23', '1234567132', 'OTHER', 2, FALSE),
('user24@gmail.com', '$2a$12$H.qOjLkO.91heJjKmpDV7OJjyU5BD9YFdQRzp9lqcTjYZektVOC7i', 'Trần D', 'avatar24.jpg', 'Address 24', '1234563211', 'MALE', 2, FALSE),
('user25@gmail.com', '$2a$12$3h.4jB.cYKr48yrEx4bllOX/Y0ATeoHSmGo9ug39qppmfRxNRrRF6', 'Thiên A', 'avatar25.jpg', 'Address 25', '1234565466', 'FEMALE', 2, FALSE),
('user26@gmail.com', '$2a$12$uDkEOi4LEl.aANkb.Ifa6uJCVdZeGUqG4ylxNPmyvvMb30fJzCYN6', 'Thanh F', 'avatar26.jpg', 'Address 26', '123458768', 'OTHER', 2, FALSE),
('user27@gmail.com', '$2a$12$dlM/rMGkVLzn.bQRgB/FgernpBj6.PC1zrBJzoYkj0ORF4cGPhHNm', 'Mỹ K', 'avatar27.jpg', 'Address 27', '1234123335', 'MALE', 3, FALSE),
('user28@gmail.com', '$2a$12$RbuGsYWi2oCcOft7wmVHFuB6tWZT5HptFypQ4/jv/uKFUPln5lZDu', 'Minh L', 'avatar28.jpg', 'Address 28', '123456828', 'FEMALE', 3, FALSE),
('user29@gmail.com', '$2a$12$Qytg.9TZS0lUPRq7AWCcsu6X73/cqMF8G/v2SUwlJiqf1PuiyUPsm', 'Hữu P', 'avatar29.jpg', 'Address 29', '12345459', 'OTHER', 3, FALSE),
('user30@gmail.com', '$2a$12$0BjuqeYWgKWCyC7FIvgwdefLfc/d3ZeAUVZ/T/1ptxFeL1Qbj97BC', 'David Nguyễn', 'avatar30.jpg', 'Address 30', '1234244913', 'MALE', 3, FALSE);
//----------------INSERT HOTEL --------//



INSERT INTO hotels  (name,user_id,phone,rating,is_deleted,checkin_time,checkout_time,open_time,close_time,description)
VALUES
('Annata Beach Hotel',7,'0868700430',4.8,FALSE,'10:00:00','11:00:00','6:00:00','23:00:00','Tọa lạc ở Vũng Tàu, cách Bãi Sau 2 phút đi bộ, Annata Beach Hotel cung cấp chỗ nghỉ có khu vườn, chỗ đậu xe riêng miễn phí, phòng chờ chung và sân hiên. Ngoài Wi-Fi miễn phí, khách sạn 3 sao này còn cung cấp bếp chung và dịch vụ phòng. Chỗ nghỉ này cung cấp quầy lễ tân 24 giờ, dịch vụ tiền sảnh và tổ chức các tour du lịch cho khách.

Phòng khách được trang bị điều hòa, TV màn hình phẳng có truyền hình cáp, tủ lạnh, ấm đun nước, vòi xịt/chậu rửa vệ sinh, đồ vệ sinh cá nhân miễn phí và bàn làm việc. Ngoài phòng tắm riêng, vòi sen và máy sấy tóc, các phòng tại khách sạn đều có view thành phố. Tại Annata Beach Hotel, phòng nào cũng có ga trải giường và khăn tắm.

Khách có thể thưởng thức bữa sáng thực đơn buffet, thực đơn à la carte hoặc kiểu lục địa tại chỗ nghỉ.')

INSERT INTO hotels  (name,user_id,phone,rating,is_deleted,checkin_time,checkout_time,open_time,close_time,description)
VALUES
('Căn hộ The Sóng Vũng Tàu',8,'0934623421',4.5,FALSE,'9:00:00','10:00:00','7:00:00','22:00:00','Với hồ bơi, khu vườn, phòng chờ chung và nhìn ra núi, Căn hộ The Sóng Vũng Tàu nằm ở Vũng Tàu cung cấp chỗ nghỉ có Wi-Fi miễn phí. Chỗ đậu xe riêng có sẵn trong khuôn viên.

Căn hộ có sân hiên nơi khách có thể nhìn ra thành phố, khu vực ghế ngồi, TV màn hình phẳng truyền hình vệ tinh, bếp đầy đủ tiện nghi gồm tủ lạnh và lò vi sóng, cùng phòng tắm riêng được trang bị vòi sen và đồ vệ sinh cá nhân miễn phí. Bếp và ấm đun nước đều được cung cấp.

Căn hộ The Sóng Vũng Tàu cách Bãi Sau chưa đến 1 km và Khu du lịch sinh thái văn hóa Hồ Mây 3.7 km.')

INSERT INTO hotels  (name,user_id,phone,rating,is_deleted,checkin_time,checkout_time,open_time,close_time,description)
VALUES
('Volga Hotel',12,'0343287247',4.6,FALSE,'8:00:00','9:00:00','8:00:00','23:00:00','Tọa lạc ở Vũng Tàu, cách Bãi Sau chỉ vài bước chân, Volga Hotel cung cấp chỗ nghỉ có hồ bơi ngoài trời, chỗ đậu xe riêng miễn phí và nhà hàng. Khách sạn 3 sao này cung cấp dịch vụ phòng, quầy lễ tân 24 giờ và Wi-Fi miễn phí. Đây là chỗ nghỉ không gây dị ứng và nằm cách Bãi Dứa 18 phút đi bộ.

Với phòng tắm riêng được trang bị đồ vệ sinh cá nhân miễn phí, các phòng tại khách sạn có TV màn hình phẳng và điều hòa, trong đó một số phòng có ban công. Phòng khách đều có tủ quần áo và ấm đun nước để khách sử dụng.

Volga Hotel cách Bãi Trước 2.7 km và Mũi Nghinh Phong 15 phút đi bộ.')

INSERT INTO hotels  (name,user_id,phone,rating,is_deleted,checkin_time,checkout_time,open_time,close_time,description)
VALUES
('Hafi Beach Hotel',17,'0864237437',4.8,FALSE,'10:00:00','11:00:00','6:00:00','23:00:00','Nằm ở Vũng Tàu, cách Bãi Thủy Tiên 2 phút đi bộ, Hafi Beach Hotel cung cấp chỗ nghỉ có hồ bơi ngoài trời, chỗ đậu xe riêng miễn phí, khu vườn và sân hiên. Ngoài nhà hàng, khách sạn 3 sao này còn có các phòng với điều hòa được trang bị Wi-Fi miễn phí, trong đó mỗi phòng đều có phòng tắm riêng. Chỗ nghỉ này cung cấp dịch vụ phòng và quầy lễ tân 24 giờ cho khách.

Tại khách sạn, các phòng được thiết kế có ban công với view vườn. Các căn ở Hafi Beach Hotel được trang bị TV màn hình phẳng và máy sấy tóc.

Chỗ nghỉ có phục vụ bữa sáng kiểu Á mỗi buổi sáng.')

INSERT INTO hotels  (name,user_id,phone,rating,is_deleted,checkin_time,checkout_time,open_time,close_time,description)
VALUES
('YK Home - The Song Apartment',18,'0934623421',4.9,FALSE,'9:00:00','10:00:00','7:00:00','22:00:00','Với hồ bơi, khu vườn, phòng chờ chung và nhìn ra biển, YK Home - The Song Apartment tọa lạc ở Vũng Tàu cung cấp chỗ nghỉ có Wi-Fi miễn phí. Chỗ đậu xe riêng có sẵn trong khuôn viên.

Mỗi căn đều có sân hiên nhìn ra thành phố, TV màn hình phẳng, khu vực ghế ngồi, bếp tiện nghi, cùng phòng tắm riêng có vòi xịt/chậu rửa vệ sinh, đồ vệ sinh cá nhân miễn phí và máy sấy tóc. Tất cả các căn đều được thiết kế có ban công nhìn ra núi.

Căn hộ cung cấp chỗ nghỉ 4 sao với phòng xông hơi khô và sân chơi trẻ em.

Khách có thể ghé công viên nước và sử dụng câu lạc bộ trẻ em tại YK Home - The Song Apartment.

Chỗ nghỉ cách Bãi Sau chưa đến 1 km và Khu du lịch sinh thái văn hóa Hồ Mây 3.7 km.')

INSERT INTO hotels  (name,user_id,phone,rating,is_deleted,checkin_time,checkout_time,open_time,close_time,description)
VALUES
('Astana Villa Non Nuoc Beach',19,'034395374',4.6,FALSE,'8:00:00','9:00:00','8:00:00','23:00:00','Astana Villa Non Nuoc Beach có hồ bơi ngoài trời, trung tâm thể dục, khu vườn và nhà hàng ở Đà Nẵng. Chỗ nghỉ này có các tiện nghi như quầy bar và khu vực bãi biển riêng. Chỗ nghỉ cung cấp lễ tân 24/24, dịch vụ đưa đón sân bay, câu lạc bộ trẻ em và Wi-Fi miễn phí ở toàn bộ chỗ nghỉ.

Resort sẽ cung cấp cho khách các phòng có điều hòa, bàn làm việc, ấm đun nước, tủ lạnh, lò vi sóng, két an toàn, TV màn hình phẳng, sân hiên, phòng tắm riêng và vòi sen. Tại Astana Villa Non Nuoc Beach, các phòng đều đi kèm với ga trải giường và khăn tắm.

Chỗ nghỉ có phục vụ bữa sáng thực đơn buffet, kiểu lục địa hoặc kiểu Anh/ Ai-len mỗi buổi sáng.

Chỗ nghỉ có sân chơi trẻ em. Astana Villa Non Nuoc Beach có dịch vụ thuê xe đạp và dịch vụ thuê ô tô. Ngoài ra, leo núi và đi xe đạp là hoạt động được ưa chuộng trong khu vực.

Các điểm tham quan nổi tiếng gần resort bao gồm Bãi tắm Tân Trà, Bãi biển Non Nước và Sân golf Montgomerie Links. Sân bay gần nhất là Sân bay Quốc tế Đà Nẵng, cách Astana Villa Non Nuoc Beach 12 km.')

INSERT INTO hotels  (name,user_id,phone,rating,is_deleted,checkin_time,checkout_time,open_time,close_time,description)
VALUES
('Salamander Apartment hotel',20,'328472381',4.0,FALSE,'8:00:00','9:00:00','8:00:00','23:00:00','Tọa lạc ở Đà Nẵng, Salamander Apartment hotel cung cấp Wi-Fi miễn phí, nơi khách có thể trải nghiệm xe đạp miễn phí, phòng chờ chung và sân hiên.

Một số căn có TV màn hình phẳng truyền hình vệ tinh, bếp đầy đủ tiện nghi với tủ lạnh, cùng phòng tắm riêng được trang bị vòi xịt/chậu rửa vệ sinh và đồ vệ sinh cá nhân miễn phí.

Khách sạn căn hộ có dịch vụ cho thuê ô tô.

Salamander Apartment hotel cách Bãi biển Mỹ Khê 8 phút đi bộ và Cầu sông Hàn 2.6 km. Sân bay gần nhất là Sân bay Quốc tế Đà Nẵng, cách chỗ nghỉ 7 km.')

INSERT INTO hotels  (name,user_id,phone,rating,is_deleted,checkin_time,checkout_time,open_time,close_time,description)
VALUES
('Anstay Beach Da Nang',22,'436412911',4.2,FALSE,'8:00:00','9:00:00','8:00:00','23:00:00','Nằm ở Đà Nẵng gần Bãi biển Mỹ Khê, Anstay Beach Da Nang cung cấp Wi-Fi miễn phí và chỗ đậu xe riêng miễn phí.

Tất cả các căn có phòng tắm riêng, vòi xịt/chậu rửa vệ sinh, điều hòa, TV màn hình phẳng và tủ lạnh. Một số căn còn có bếp được trang bị lò vi sóng và bếp.

Khách có thể tìm thấy khu vực bãi biển riêng tại khách sạn căn hộ, cùng sân hiên.

Anstay Beach Da Nang cách Cầu sông Hàn 3.8 km và Cầu khóa Tình yêu Đà Nẵng 4.3 km. Sân bay gần nhất là Sân bay Quốc tế Đà Nẵng, cách chỗ nghỉ 7 km, đồng thời chỗ nghỉ có cung cấp dịch vụ đưa đón sân bay mất phí.')

INSERT INTO hotels  (name,user_id,phone,rating,is_deleted,checkin_time,checkout_time,open_time,close_time,description)
VALUES
('May Studio',27,'081273211',4.3,FALSE,'10:00:00','11:00:00','6:00:00','23:00:00','Nằm ở Đà Lạt, cách Quảng trường Lâm Viên 1.7 km và Sân golf Dalat Palace Golf Club 1.9 km, May Studio cung cấp chỗ nghỉ nhìn ra thành phố, có Wi-Fi miễn phí và khu vườn với sân hiên.

Căn hộ có ban công, khu vực ghế ngồi, TV màn hình phẳng truyền hình cáp, bếp đầy đủ tiện nghi gồm tủ lạnh và lò vi sóng, cùng phòng tắm riêng được trang bị vòi sen và máy sấy tóc. Để thuận tiện hơn cho khách, chỗ nghỉ có thể cung cấp khăn tắm và khăn trải giường với khoản phụ phí.

May Studio cách Hồ Xuân Hương 1.9 km và Vườn hoa Đà Lạt 2.1 km. Sân bay gần nhất là Sân bay Liên Khương, cách chỗ nghỉ 28 km.')

INSERT INTO hotels  (name,user_id,phone,rating,is_deleted,checkin_time,checkout_time,open_time,close_time,description)
VALUES
('Lavie Villa',28,'03213261',4.9,FALSE,'9:00:00','10:00:00','7:00:00','22:00:00','Cách Công viên Yersin 2.8 km, Lavie Villa có khu vườn, phòng chờ chung, điều hòa, ban công và Wi-Fi miễn phí.
Biệt thự có sân hiên nơi khách có thể nhìn ra thành phố, khu vực ghế ngồi, TV màn hình phẳng truyền hình cáp, bếp đầy đủ tiện nghi gồm tủ lạnh và lò nướng, cùng phòng tắm riêng được trang bị vòi xịt/chậu rửa vệ sinh và đồ vệ sinh cá nhân miễn phí. Để thuận tiện hơn cho khách, chỗ nghỉ có thể cung cấp khăn tắm và khăn trải giường với khoản phụ phí.
Lavie Villa có BBQ.Chỗ nghỉ có dịch vụ cho thuê ô tô.Lavie Villa cách Hồ Xuân Hương 2.8 km và Quảng trường Lâm Viên 3.1 km. Sân bay gần nhất là Sân bay Liên Khương, cách biệt thự 25 km, đồng thời chỗ nghỉ có cung cấp dịch vụ đưa đón sân bay mất phí.')

INSERT INTO hotels  (name,user_id,phone,rating,is_deleted,checkin_time,checkout_time,open_time,close_time,description)
VALUES
('Romeo & Juliet Dalat Resort',29,'0327123293',4.6,FALSE,'8:00:00','9:00:00','8:00:00','23:00:00','Tọa lạc ở Đà Lạt, cách Hồ Tuyền Lâm 2.7 km, Romeo & Juliet Dalat Resort cung cấp chỗ nghỉ có khu vườn, chỗ đậu xe riêng miễn phí, sân hiên và nhà hàng. Khách sạn 3 sao này có máy ATM và bàn bán tour. Chỗ nghỉ cung cấp lễ tân 24/24, dịch vụ đưa đón sân bay, dịch vụ phòng và Wi-Fi miễn phí ở toàn bộ chỗ nghỉ.

Tại khách sạn, mỗi phòng đều được thiết kế có tủ quần áo, ban công với view núi, phòng tắm riêng, TV màn hình phẳng, ga trải giường và khăn tắm. Tại Romeo & Juliet Dalat Resort, các phòng đều có khu vực ghế ngồi.

Khách tại chỗ nghỉ có thể thưởng thức bữa sáng thực đơn à la carte hoặc kiểu Á.')

//-----------------INSERT HOTEL avatar---------------//
UPDATE hotels
SET avatar = 'https://cf.bstatic.com/xdata/images/hotel/max1024x768/492735048.jpg?k=117fccb561904afe3b4a16b58b41171c9f894bdd9ccb8300e543932e87ba1c2a&o=&hp=1'
WHERE id = 1;

UPDATE hotels
SET avatar = 'https://cf.bstatic.com/xdata/images/hotel/max1024x768/518881506.jpg?k=a27b736d225ccf0ca41d21c55a57b0f657dfb33452d249ec8110dfe22de8c643&o=&hp=1'
WHERE id = 2;

UPDATE hotels
SET avatar = 'https://cf.bstatic.com/xdata/images/hotel/max1024x768/496144947.jpg?k=621c09b27044c74c87e6025df6075743264cb6993dc499fd4b3428b4c3cc0093&o=&hp=1'
WHERE id = 3;

UPDATE hotels
SET avatar = 'https://cf.bstatic.com/xdata/images/hotel/max1024x768/539603833.jpg?k=33f56739065390d1ccf11b73559427de4705cab5721f2c2e4afde1376ee7896d&o=&hp=1'
WHERE id = 4;

UPDATE hotels
SET avatar = 'https://cf.bstatic.com/xdata/images/hotel/max1024x768/512142631.jpg?k=b580c867cf72f2d2f482439917e0b63bc0eb483efd776f1d79719defdc151a44&o=&hp=1'
WHERE id = 5;

UPDATE hotels
SET avatar = 'https://cf.bstatic.com/xdata/images/hotel/max1024x768/558210825.jpg?k=7ea0b4559ede29ffe46b770d1d5f727ade777056cbb9a5047b70cda2d887cfac&o=&hp=1'
WHERE id = 6;

UPDATE hotels
SET avatar = 'https://cf.bstatic.com/xdata/images/hotel/max1024x768/125541820.jpg?k=b28079ea137046f52e4d631a05d4f462f67cb35be2efbed85ae05ff6c54374b8&o=&hp=1'
WHERE id = 7;

UPDATE hotels
SET avatar = 'https://cf.bstatic.com/xdata/images/hotel/max1024x768/470727150.jpg?k=3b902f1eeb38d5230e09a1d38e02f54aa085af4faefc95d61a420daa2e21251a&o=&hp=1'
WHERE id = 8;

UPDATE hotels
SET avatar = 'https://cf.bstatic.com/xdata/images/hotel/max1024x768/249212762.jpg?k=f2db871f6071336c78666f46ca864a4fc59674f638726eb823b56ebb0e3ea960&o=&hp=1'
WHERE id = 9;

UPDATE hotels
SET avatar = 'https://cf.bstatic.com/xdata/images/hotel/max1024x768/545079242.jpg?k=1324303564410671a4348c93aad5244fdc37ad127cf346c87b7bbb27b815d569&o=&hp=1'
WHERE id = 10;

UPDATE hotels
SET avatar = 'https://cf.bstatic.com/xdata/images/hotel/max1024x768/464234366.jpg?k=a11c84b3a639abfc16e38d952159cd713d67c1806b794a6ce78b5014930039b3&o=&hp=1'
WHERE id =11;



// ------------------INSERT HOTEL address---------------//

INSERT INTO hotel_address (hotel_id,street_number,street_name,district,province,city,country)
VALUES
(1, '165','Thuy Van','Thang Tam','Bà Rịa - Vũng Tàu','Vung Tau','VIET NAM'),
(2,'28','Thi Sách','Quận Thi Sách','Bà Rịa - Vũng Tàu', 'Vung Tau', 'VIET NAM'),
(3,'15','Thuỳ Vân','','Bà Rịa - Vũng Tàu', 'Vung Tau', 'VIET NAM'),
(4,'68','Hà Huy Tập','Phường 10','Bà Rịa - Vũng Tàu', 'Vung Tau', 'VIET NAM'),
(5,'28','Thi sách','','Bà Rịa - Vũng Tàu', 'Vung Tau', 'VIET NAM'),
(6,'10','The ocean suites','','Quảng Nam', 'Da Nang', 'VIET NAM'),
(7,'17','Le Thuoc','Da nang','Quảng Nam', 'Da Nang', 'VIET NAM'),
(8,'8','Le Boi','Sơn Trà','Quảng Nam', 'Da Nang', 'VIET NAM'),
(9,'15','Đống Đa','Phường 3','Lâm Đồng', 'Da Lat', 'VIET NAM'),
(10,'15','Hoa Phượng Tím','Phường 3','Lâm Đồng', 'Da Lat', 'VIET NAM'),
(11,'49','Bùi Thị Xuân','','Lâm Đồng', 'Da Lat', 'VIET NAM');


//--------------------INSERT Hotel image-------------//

INSERT INTO hotel_image (hotel_id,image_title,image_description,image_path)
VALUES
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/492735048.jpg?k=117fccb561904afe3b4a16b58b41171c9f894bdd9ccb8300e543932e87ba1c2a&o=&hp=1'),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/345769524.jpg?k=518f3068da947c1f150c6b0a2cbca82f458a1fecfa08560317b183198dfa3a5d&o=&hp=1'),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/345770374.jpg?k=25aa0376208f7c51042bd9aca278811ab0fda9a3a58e7f01509d93e34e5b9cda&o=&hp=1'),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/492735041.jpg?k=6ec96b96cdea3cc44860fb4a01a6f8f0cf47373b5eb672e3c18b7f6c7334eaee&o=&hp=1'),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/345769487.jpg?k=d8a80778302aeba2d4005c0bbfa526f42fffd2b251eefee6a9802b102ddc8625&o=&hp=1'),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/492730634.jpg?k=981eb1a0e87e5ee74a15ae24831884ceda36650f817a3cea81f05c5edfc31ee6&o=&hp=1'),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/348059145.jpg?k=12bf2b1c88563e6058f63be10317069053fb9b41f9911d20a27d16a7c2f8691c&o=&hp=1'),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/492735036.jpg?k=07b0e0c695fb5b6560e72373aa8fcd57c4d18ae283310929ba2a348863456871&o=&hp=1'),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/492735053.jpg?k=a5a986e08acd85eff9f78c73112650896386cc48c69ebfe847bdc5194875b0ac&o=&hp=1');


INSERT INTO hotel_image (hotel_id,image_title,image_description,image_path)
VALUES
(2,'Căn hộ The Sóng Vũng Tàu','Căn hộ The Sóng Vũng Tàu','https://cf.bstatic.com/xdata/images/hotel/max1024x768/518881506.jpg?k=a27b736d225ccf0ca41d21c55a57b0f657dfb33452d249ec8110dfe22de8c643&o=&hp=1'),
(2,'Căn hộ The Sóng Vũng Tàu','Căn hộ The Sóng Vũng Tàu','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552810215.jpg?k=3e6042352b78a91b36297524ec5d6bb8870d45967bf75a87e046a0da5eff6b49&o=&hp=1'),
(2,'Căn hộ The Sóng Vũng Tàu','Căn hộ The Sóng Vũng Tàu','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552810354.jpg?k=2eb75c1c092acc4fd28ff9bdc58a6de2659e18f7a759fe22789e44e8787fef97&o=&hp=1'),
(2,'Căn hộ The Sóng Vũng Tàu','Căn hộ The Sóng Vũng Tàu','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552810307.jpg?k=86b9a3c094bec400f58580242e7a74a7932865ce2167ee1dbafa80547f9b0656&o=&hp=1'),
(2,'Căn hộ The Sóng Vũng Tàu','Căn hộ The Sóng Vũng Tàu','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552810233.jpg?k=fcd777e65774d1a3f276d87033f85bd9e4c28787157562df3840e1e1068596e6&o=&hp=1'),
(2,'Căn hộ The Sóng Vũng Tàu','Căn hộ The Sóng Vũng Tàu','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552810147.jpg?k=0808217b18a057e4859db9e9e32485d2d2090fa5630a76e728440872fd86b963&o=&hp=1'),
(2,'Căn hộ The Sóng Vũng Tàu','Căn hộ The Sóng Vũng Tàu','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552810152.jpg?k=9a75b788fb5d2dd5347cb479936adcf54a1b8ad99e3ed0efe711b1c0305ea360&o=&hp=1'),
(2,'Căn hộ The Sóng Vũng Tàu','Căn hộ The Sóng Vũng Tàu','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552810156.jpg?k=01c9f3c2ad8344630e689177d170698429f8392b9ee11a66ca614a21b1245f3f&o=&hp=1'),
(2,'Căn hộ The Sóng Vũng Tàu','Căn hộ The Sóng Vũng Tàu','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552810208.jpg?k=f03f113315e12531a423afd8a1bf38d8d95a23096b6138b32d683947e1dabbe5&o=&hp=1'),
(2,'Căn hộ The Sóng Vũng Tàu','Căn hộ The Sóng Vũng Tàu','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552810267.jpg?k=302b8b5732daba6f28be3f8a051de89c2d51acd0e465a86abf9f429a2d45ce84&o=&hp=1');



INSERT INTO hotel_image (hotel_id,image_title,image_description,image_path)
VALUES
(3,'Volga Hotel','Volga Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/254532846.jpg?k=e355d889821b4c3a0c3d10abc9af0ba110426afb519d3db5064a0c8470577d3a&o=&hp=1'),
(3,'Volga Hotel','Volga Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/496144947.jpg?k=621c09b27044c74c87e6025df6075743264cb6993dc499fd4b3428b4c3cc0093&o=&hp=1'),
(3,'Volga Hotel','Volga Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/496139404.jpg?k=fa7e8db6190e1af06acf9a90c511fd8a192d457e33c64c1310544814206aa7d0&o=&hp=1'),
(3,'Volga Hotel','Volga Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/252877804.jpg?k=d1b86375dc56f04ff393dc2f45cd77e828ae7fc4e8fc4ba18b8caf8b3da6554d&o=&hp=1'),
(3,'Volga Hotel','Volga Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/496139950.jpg?k=3fa339ce4086dcfda1b09d3d0ff08ce33ab8b037e6e19c656ee7258ca361b678&o=&hp=1'),
(3,'Volga Hotel','Volga Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/496139774.jpg?k=21af387bc748d17c367efc88189a8b64536691d27c2c2996e240a0ab6c0c29ca&o=&hp=1'),
(3,'Volga Hotel','Volga Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/298105304.jpg?k=d4377524fffb41e2a9cb95444b21f0c3857a4973ec6cf085553f37be508c5e26&o=&hp=1'),
(3,'Volga Hotel','Volga Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/298105305.jpg?k=020fbe22eb02a0f1e7a2f6be77e48c65ff324ced0724865fd9285411fcf31e62&o=&hp=1'),
(3,'Volga Hotel','Volga Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/342828623.jpg?k=f08cf8d22f3b125c9312770a83a78ac359ef991fbd1a4223640a80677cf5d26a&o=&hp=1'),
(3,'Volga Hotel','Volga Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/254532748.jpg?k=be957752f91a0c66e53cf640319b7615fb7d4c9d7cdf8bbf7a150bf88ed7ea61&o=&hp=1');


INSERT INTO hotel_image (hotel_id,image_title,image_description,image_path)
VALUES
(4,'Hafi Beach Hotel','Hafi Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/539603833.jpg?k=33f56739065390d1ccf11b73559427de4705cab5721f2c2e4afde1376ee7896d&o=&hp=1'),
(4,'Hafi Beach Hotel','Hafi Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/349737609.jpg?k=1457108586988a172603f9b8ebb1bd8dacebde167118e150c8ba49e19614ea65&o=&hp=1'),
(4,'Hafi Beach Hotel','Hafi Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/539603023.jpg?k=5b1a1e4d048692aa761f8cf1a859ecdced19034023ae4ddd7d5b4dd8eeb71d0b&o=&hp=1'),
(4,'Hafi Beach Hotel','Hafi Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/539603024.jpg?k=73a0d4e8cd49f00073bc0df53057207652c6f2adb42954933d6768283e956bcd&o=&hp=1'),
(4,'Hafi Beach Hotel','Hafi Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/539603660.jpg?k=544d6012327386ba3674a7148041011bc648f082c13c84b55ea0c5c70fd6eae2&o=&hp=1'),
(4,'Hafi Beach Hotel','Hafi Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/539603724.jpg?k=9c8b42b464b1cbd7b8d3463c5191b899e13910e121a37efdeb444006a614b07b&o=&hp=1'),
(4,'Hafi Beach Hotel','Hafi Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/538334736.jpg?k=33d3707d74ec4bba1985941b516101da3c2e7143173bb541daa76b93cac0b261&o=&hp=1'),
(4,'Hafi Beach Hotel','Hafi Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/539603920.jpg?k=51765012a5c12c200c1016fef9a3d14725c1cc94b4b7ca0899f4cc5cb3391f24&o=&hp=1'),
(4,'Hafi Beach Hotel','Hafi Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/349847306.jpg?k=618282e81d7ddcfc919069b29e67a45cf1c91e66f2d8c76aaff5a4b24fb5df23&o=&hp=1'),
(4,'Hafi Beach Hotel','Hafi Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/539603021.jpg?k=b3519bee6e5ce40de81f174cd786ee794d22f37c76e0df3291f096b1950d287b&o=&hp=1');


INSERT INTO hotel_image (hotel_id,image_title,image_description,image_path)
VALUES
(5,'YK Home - The Song Apartment','YK Home - The Song Apartment','https://cf.bstatic.com/xdata/images/hotel/max1024x768/512142631.jpg?k=b580c867cf72f2d2f482439917e0b63bc0eb483efd776f1d79719defdc151a44&o=&hp=1'),
(5,'YK Home - The Song Apartment','YK Home - The Song Apartment','https://cf.bstatic.com/xdata/images/hotel/max1024x768/510291678.jpg?k=34764b38fdec91b611e98bb309905ad726eeea6c9f30180bb846fa2bdfda94a0&o=&hp=1'),
(5,'YK Home - The Song Apartment','YK Home - The Song Apartment','https://cf.bstatic.com/xdata/images/hotel/max1024x768/512142287.jpg?k=a3923d8ee270ae36a61225ab768af6316cb06d492c30adbd079b5d21b3fe7e7a&o=&hp=1'),
(5,'YK Home - The Song Apartment','YK Home - The Song Apartment','https://cf.bstatic.com/xdata/images/hotel/max1024x768/524243742.jpg?k=9a53cf26ad8ea148bcbeb1aca3bbdfd7a633f34678b2dc8816c8042fa92cff5c&o=&hp=1'),
(5,'YK Home - The Song Apartment','YK Home - The Song Apartment','https://cf.bstatic.com/xdata/images/hotel/max1024x768/513910557.jpg?k=6990ff95c17170675869ad770d2942073454af6ee4f00d846ab8cccb7102c91f&o=&hp=1'),
(5,'YK Home - The Song Apartment','YK Home - The Song Apartment','https://cf.bstatic.com/xdata/images/hotel/max1024x768/512142289.jpg?k=2663fe3840f64a1bfb946b0ce5ac6631a2d89e0ce35275a383357be7e09a295e&o=&hp=1'),
(5,'YK Home - The Song Apartment','YK Home - The Song Apartment','https://cf.bstatic.com/xdata/images/hotel/max1024x768/513910577.jpg?k=3c1d304a1d9db175c122500bc0474e353460ac05128869868ead259f366b94ac&o=&hp=1'),
(5,'YK Home - The Song Apartment','YK Home - The Song Apartment','https://cf.bstatic.com/xdata/images/hotel/max1024x768/513910596.jpg?k=b1c791e61d440827c792b9da347242997d3588d158a93f9e446a2b0b5734df00&o=&hp=1'),
(5,'YK Home - The Song Apartment','YK Home - The Song Apartment','https://cf.bstatic.com/xdata/images/hotel/max1024x768/509730299.jpg?k=c3cdf252773ef1141ebde5bdbedd1440dd11803a4fc3655c7d409b9901b1e77c&o=&hp=1'),
(5,'YK Home - The Song Apartment','YK Home - The Song Apartment','https://cf.bstatic.com/xdata/images/hotel/max1024x768/509730316.jpg?k=6d3b016f8e04b1cb803a6be7ec3b5202fb8494a37b781d42176c0d653c57ce5a&o=&hp=1');


INSERT INTO hotel_image (hotel_id,image_title,image_description,image_path)
VALUES
(6,'Astana Villa Non Nuoc Beach','Astana Villa Non Nuoc Beach','https://cf.bstatic.com/xdata/images/hotel/max1024x768/558210825.jpg?k=7ea0b4559ede29ffe46b770d1d5f727ade777056cbb9a5047b70cda2d887cfac&o=&hp=1'),
(6,'Astana Villa Non Nuoc Beach','Astana Villa Non Nuoc Beach','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552650637.jpg?k=3ebb3aa337fc01dbf22585bb8ae77d1dd11349d0a18016ed2d6dee7bfea837e5&o=&hp=1'),
(6,'Astana Villa Non Nuoc Beach','Astana Villa Non Nuoc Beach','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552669236.jpg?k=3bfe961306343c3bc4c017cb996b9dca79f924985ce127ec9249200a7660fef0&o=&hp=1'),
(6,'Astana Villa Non Nuoc Beach','Astana Villa Non Nuoc Beach','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552658858.jpg?k=491c466c7a3b37ac85bd0f59b7973bd832df10fca0c2c1c5b97290c95fb5d9fb&o=&hp=1'),
(6,'Astana Villa Non Nuoc Beach','Astana Villa Non Nuoc Beach','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552661932.jpg?k=65c421577a52ef0e9ff95a6a341c9c76441da9404b31e05f00ff57104e38f358&o=&hp=1'),
(6,'Astana Villa Non Nuoc Beach','Astana Villa Non Nuoc Beach','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552656263.jpg?k=780f8357366b65be2ccbc7c5e735929ffce5a3d80a2c6d859c0e2eb69bd798cd&o=&hp=1'),
(6,'Astana Villa Non Nuoc Beach','Astana Villa Non Nuoc Beach','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552650663.jpg?k=76e8174a515684ff1b95bf5d533052d44ab137d8a4a16f76eb3b0544528e159d&o=&hp=1'),
(6,'Astana Villa Non Nuoc Beach','Astana Villa Non Nuoc Beach','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552662007.jpg?k=6ab88c741d0ea3bab84e92507b03e69ac0d6de00557af98fcca29f7f146b23b2&o=&hp=1'),
(6,'Astana Villa Non Nuoc Beach','Astana Villa Non Nuoc Beach','https://cf.bstatic.com/xdata/images/hotel/max1024x768/552655692.jpg?k=02480e081f18f02c4ccb0470129a169f6ec0d272c53d8d7619da1746e5b98cab&o=&hp=1'),
(6,'Astana Villa Non Nuoc Beach','Astana Villa Non Nuoc Beach','https://cf.bstatic.com/xdata/images/hotel/max1024x768/555146297.jpg?k=5153865dbe37f3f69921beff444bbabe7c3146754ed7595500e156ebc0653b47&o=&hp=1');

INSERT INTO hotel_image (hotel_id,image_title,image_description,image_path)
VALUES
(7,'Salamander Apartment hotel','Salamander Apartment hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/125541820.jpg?k=b28079ea137046f52e4d631a05d4f462f67cb35be2efbed85ae05ff6c54374b8&o=&hp=1'),
(7,'Salamander Apartment hotel','Salamander Apartment hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/110547679.jpg?k=10a515e1de3918c39d30f675611d76b2922a84ff8b8d25c9fe8410636b5b5f86&o=&hp=1'),
(7,'Salamander Apartment hotel','Salamander Apartment hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/109037167.jpg?k=86124660774a6ee470620cb1e7a515ccbec74c7c3d1cdefd6f9d205195300b50&o=&hp=1'),
(7,'Salamander Apartment hotel','Salamander Apartment hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/131028269.jpg?k=b38f588c6eada8a44f4564b105b7713c6a826220f3a535b264859aef4f5d9e34&o=&hp=1'),
(7,'Salamander Apartment hotel','Salamander Apartment hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/532796129.jpg?k=e69fac942d79cd3883cdf7e507fcac9cb1d62c5d8378a189e34a44a61e79953e&o=&hp=1'),
(7,'Salamander Apartment hotel','Salamander Apartment hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/109037860.jpg?k=42197210d11cd35cf0d32a95a9225698ff25ff86ab47bce8a38368192a1aa54d&o=&hp=1'),
(7,'Salamander Apartment hotel','Salamander Apartment hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/110547676.jpg?k=d5d23100c0d45817f3771955fad7dd3411e54df76b1c28654cee0a490dbcc110&o=&hp=1'),
(7,'Salamander Apartment hotel','Salamander Apartment hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/511704282.jpg?k=12d3ab2204b7a02703fc63a2f94ce8dd8d97efab31354bd2e34f5b4a800eac67&o=&hp=1'),
(7,'Salamander Apartment hotel','Salamander Apartment hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/109037853.jpg?k=7be22a9309a9788a1fb9984d4427fc42840df861e7be35396c1b38dc776bf8ed&o=&hp=1'),
(7,'Salamander Apartment hotel','Salamander Apartment hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/109037874.jpg?k=2fdef814defaa0d1d1c8f84033e562549a482702976d44a6b46b2387b3011cc0&o=&hp=1');

INSERT INTO hotel_image (hotel_id,image_title,image_description,image_path)
VALUES
(8,'Anstay Beach Da Nang','Anstay Beach Da Nang','https://cf.bstatic.com/xdata/images/hotel/max1024x768/496288433.jpg?k=80f464a43d35e35d9d4c1d9c4e1c70615e716f898e58caab7f8a4252705784f2&o=&hp=1'),
(8,'Anstay Beach Da Nang','Anstay Beach Da Nang','https://cf.bstatic.com/xdata/images/hotel/max1024x768/496288527.jpg?k=7dcf9b89f46995ff0b3a9a644f7244f0b345b182a9ac4e76efdc10d1e4b331e8&o=&hp=1'),
(8,'Anstay Beach Da Nang','Anstay Beach Da Nang','https://cf.bstatic.com/xdata/images/hotel/max1024x768/451342478.jpg?k=cc93201668f8bba805e96daf0669a61a42568614c191a87ea3faf9fc85985a59&o=&hp=1'),
(8,'Anstay Beach Da Nang','Anstay Beach Da Nang','https://cf.bstatic.com/xdata/images/hotel/max1024x768/496288491.jpg?k=a7195c3f95ce5bcad80f742234e336ddb14eefe7521f88daf1fdbeab254a62b2&o=&hp=1'),
(8,'Anstay Beach Da Nang','Anstay Beach Da Nang','https://cf.bstatic.com/xdata/images/hotel/max1024x768/470727150.jpg?k=3b902f1eeb38d5230e09a1d38e02f54aa085af4faefc95d61a420daa2e21251a&o=&hp=1'),
(8,'Anstay Beach Da Nang','Anstay Beach Da Nang','https://cf.bstatic.com/xdata/images/hotel/max1024x768/496288485.jpg?k=10ad284b8be4928807bf5244a9673227059c0a45796dac38290c545a0017ee77&o=&hp=1'),
(8,'Anstay Beach Da Nang','Anstay Beach Da Nang','https://cf.bstatic.com/xdata/images/hotel/max1024x768/492201000.jpg?k=e38d8459362f99eacd6ba906447141963165d2be65a5de8e6ea10d111a083cb6&o=&hp=1'),
(8,'Anstay Beach Da Nang','Anstay Beach Da Nang','https://cf.bstatic.com/xdata/images/hotel/max1024x768/496288443.jpg?k=b1de1fea9adaf413d321f99833998ca2d94ba4e992dd3951c432f54fc61480c1&o=&hp=1'),
(8,'Anstay Beach Da Nang','Anstay Beach Da Nang','https://cf.bstatic.com/xdata/images/hotel/max1024x768/496368520.jpg?k=086a97dd8472df4ced464283163edaae262e6d3f8ed479a02b234f9f6bb181f5&o=&hp=1'),
(8,'Anstay Beach Da Nang','Anstay Beach Da Nang','https://cf.bstatic.com/xdata/images/hotel/max1024x768/470726991.jpg?k=9d62e8b49f0b14c7afd077b879b10325be510c0dad76749bdcfb872fcc5ceefc&o=&hp=1');



INSERT INTO hotel_image (hotel_id,image_title,image_description,image_path)
VALUES
(9,'Lavie Villa','Lavie Villa','https://cf.bstatic.com/xdata/images/hotel/max1024x768/249212762.jpg?k=f2db871f6071336c78666f46ca864a4fc59674f638726eb823b56ebb0e3ea960&o=&hp=1'),
(9,'Lavie Villa','Lavie Villa','https://cf.bstatic.com/xdata/images/hotel/max1024x768/249216509.jpg?k=bf099f885e4c7d3c54585c275cec1a9924093a3078353e13a30f63dec1c01bdb&o=&hp=1'),
(9,'Lavie Villa','Lavie Villa','https://cf.bstatic.com/xdata/images/hotel/max1024x768/555318888.jpg?k=ae0277814fe8c9a3b1557dec87ee0f927bfcc64e331140ee05bd7d2d5c45dc1c&o=&hp=1'),
(9,'Lavie Villa','Lavie Villa','https://cf.bstatic.com/xdata/images/hotel/max1024x768/249212744.jpg?k=9a16489389cbaf68e9c80bb7556b339b6b832a9c54d008f42aadebd0b97efc40&o=&hp=1'),
(9,'Lavie Villa','Lavie Villa','https://cf.bstatic.com/xdata/images/hotel/max1024x768/249212774.jpg?k=53bb2655e227a7047fedca7f4146e602aa58da520def32840c5c4d870139089c&o=&hp=1'),
(9,'Lavie Villa','Lavie Villa','https://cf.bstatic.com/xdata/images/hotel/max1024x768/249212722.jpg?k=cc1dd72dc714f04fa3523b5b2d0c95802a84783bc2dd4a64dbc27e3b496de53d&o=&hp=1'),
(9,'Lavie Villa','Lavie Villa','https://cf.bstatic.com/xdata/images/hotel/max1024x768/555319163.jpg?k=43e3e44a349191281d7f35efcda9eb589b4cb3c5cea4f336246cf61ceb95adc5&o=&hp=1'),
(9,'Lavie Villa','Lavie Villa','https://cf.bstatic.com/xdata/images/hotel/max1024x768/249212835.jpg?k=0bd26155132907fb73674623583d7e750dc5f9bc8e8c7f629790db854cd6e05c&o=&hp=1'),
(9,'Lavie Villa','Lavie Villa','https://cf.bstatic.com/xdata/images/hotel/max1024x768/249212709.jpg?k=8b175437964a58d088eee1691f70e41b917b113ffffd8c8cd28dd488ff8480e0&o=&hp=1'),
(9,'Lavie Villa','Lavie Villa','https://cf.bstatic.com/xdata/images/hotel/max1024x768/249219485.jpg?k=7122284c23cc6e6a84cdcd9ec95a814652afac039893f01894e6b5d244126c70&o=&hp=1');


INSERT INTO hotel_image (hotel_id,image_title,image_description,image_path)
VALUES
(10,'Romeo & Juliet Dalat Resort','Romeo & Juliet Dalat Resort','https://cf.bstatic.com/xdata/images/hotel/max1024x768/545079242.jpg?k=1324303564410671a4348c93aad5244fdc37ad127cf346c87b7bbb27b815d569&o=&hp=1'),
(10,'Romeo & Juliet Dalat Resort','Romeo & Juliet Dalat Resort','https://cf.bstatic.com/xdata/images/hotel/max1024x768/545079246.jpg?k=7a3d513172b5f62d5e7fbd87304ee419c5875e24c2472e1081e38a7c18b052f0&o=&hp=1'),
(10,'Romeo & Juliet Dalat Resort','Romeo & Juliet Dalat Resort','https://cf.bstatic.com/xdata/images/hotel/max1024x768/545075097.jpg?k=e9f7f231bdf3e247ac38a117eb15490907625f394d5eb93b53f6fd0dbe689b97&o=&hp=1'),
(10,'Romeo & Juliet Dalat Resort','Romeo & Juliet Dalat Resort','https://cf.bstatic.com/xdata/images/hotel/max1024x768/545075096.jpg?k=02c6e6282a4e820ea7bebc05013861c13defe5c430032bee2f64bb10d3473218&o=&hp=1'),
(10,'Romeo & Juliet Dalat Resort','Romeo & Juliet Dalat Resort','https://cf.bstatic.com/xdata/images/hotel/max1024x768/545075095.jpg?k=19063797472f29b76d1d2fcc7c243a1354800e1cf6f5de159059f00e896c153f&o=&hp=1'),
(10,'Romeo & Juliet Dalat Resort','Romeo & Juliet Dalat Resort','https://cf.bstatic.com/xdata/images/hotel/max1024x768/545075089.jpg?k=bc91f491446562d2d0358c8abc3baf14ebcc5ab9ad6454969d9185a703ea22bf&o=&hp=1'),
(10,'Romeo & Juliet Dalat Resort','Romeo & Juliet Dalat Resort','https://cf.bstatic.com/xdata/images/hotel/max1024x768/545074331.jpg?k=f504928ec9e2c4f917c38b646b5d330b28dbf2875badf4f9d51429cef6f05f78&o=&hp=1'),
(10,'Romeo & Juliet Dalat Resort','Romeo & Juliet Dalat Resort','https://cf.bstatic.com/xdata/images/hotel/max1024x768/545079248.jpg?k=af8006cd2974fabe25b947be82c1dfccc24eb3efc32479614799820cf2f8a122&o=&hp=1'),
(10,'Romeo & Juliet Dalat Resort','Romeo & Juliet Dalat Resort','https://cf.bstatic.com/xdata/images/hotel/max1024x768/545078141.jpg?k=05e1229f2cf402b06e27427127114baf5a58094e4c5ebd897191b8d28049efe3&o=&hp=1');


INSERT INTO hotel_image (hotel_id,image_title,image_description,image_path)
VALUES
(11,'May Studio','May Studio','https://cf.bstatic.com/xdata/images/hotel/max1024x768/464234366.jpg?k=a11c84b3a639abfc16e38d952159cd713d67c1806b794a6ce78b5014930039b3&o=&hp=1'),
(11,'May Studio','May Studio','https://cf.bstatic.com/xdata/images/hotel/max1024x768/442471016.jpg?k=4ff1205c94fc31d91cc644980afe948d571473b4c776e2b3b3575589abc5e608&o=&hp=1'),
(11,'May Studio','May Studio','https://cf.bstatic.com/xdata/images/hotel/max1024x768/442471260.jpg?k=180a2a5b7e07f25b2881006648cd5e803e11812018eaa94572f81809df713ac0&o=&hp=1'),
(11,'May Studio','May Studio','https://cf.bstatic.com/xdata/images/hotel/max1024x768/179356320.jpg?k=7d626f4148b15dd6640c3653fd3f841493ce2687224f11a5ce24f89bfa2558f5&o=&hp=1'),
(11,'May Studio','May Studio','https://cf.bstatic.com/xdata/images/hotel/max1024x768/442474102.jpg?k=50ab2d9870d3358a1b9c59865b25a663a40578feb2a8735f198dd4016d9b31af&o=&hp=1'),
(11,'May Studio','May Studio','https://cf.bstatic.com/xdata/images/hotel/max1024x768/442472585.jpg?k=f194d48c72b43afd05a03f1dbc28755c7ba9b3cd54ed96826256c90d19c15271&o=&hp=1'),
(11,'May Studio','May Studio','https://cf.bstatic.com/xdata/images/hotel/max1024x768/432790409.jpg?k=0a0a66c8c375565725ee994028efc133f08b511e394338f713039440bd0982b3&o=&hp=1'),
(11,'May Studio','May Studio','https://cf.bstatic.com/xdata/images/hotel/max1024x768/179351017.jpg?k=c69315d3fcb3a746dba50caa412773ed2cdde9c98badfa8c9fb4b3b1cef7c590&o=&hp=1'),
(11,'May Studio','May Studio','https://cf.bstatic.com/xdata/images/hotel/max1024x768/432790470.jpg?k=c8279aa0921a18a9f06219da3dd4ec9ca5c6e8060b873879a7c554c3a3f79d34&o=&hp=1'),
(11,'May Studio','May Studio','https://cf.bstatic.com/xdata/images/hotel/max1024x768/442471939.jpg?k=5b8d52af45f59de6b077e1e3a203057bc69a76a88015e627bbc4534ada09baa0&o=&hp=1');

//------------------INSERT Hotel review--------------//

INSERT INTO hotel_reviews (hotel_id,user_id,comment)
VALUES
(1,2,'Tiện lợi, sạch sẽ , view đẹp. Chổ đậu xe thoải mái , ăn sáng ngon'),
(1,3,'Phòng sạch sẽ, nhân viên thân thiện, vị trí gần biển.'),
(1,4,'Phòng rộng rãi, view nhìn 1 góc biển đẹp.
Buổi tối view siêu đẹp nha. Lần sau sẽ trải nghiệm phòng view biển để góc nhìn đẹp hơn. Nhân viên phục vụ tốt, phản hồi nhanh'),
(1,9,'Giá phòng rẻ so với dịp lễ, ăn sáng ngon đa dạng, gần biển'),
(1,10,'Phòng rộng view đẹp. Phòng tắm rộng. Bồn tắm còn kèm chức năng massage'),
(1,11,'View rất đẹp chổ rất sạch sẽ nhân viên tận tình dễ chịu
Mình đi som hon gio nhận phòng mà nhân viên thu xếp cho nhận som nua rât ok luon rat hai long ve cho nghỉ ak');


INSERT INTO hotel_reviews (hotel_id,user_id,comment)
VALUES
(2,26,'Sạch sẽ, thoáng mát. Phòng có mùi tinh dầu thơm rất dễ chịu. Bạn chủ home dễ thương rất nhiệt tình hỗ trợ.'),
(2,25,'Phòng sạch sẽ .chủ nhà rất thân thiện .nhiệt tình .nội thất bên trong rất tiện nghi .K thiếu thứ gì'),
(2,24,'Phòng sạch sẽ, vị trí dễ tìm, tiện nghi toà nhà xịn sò'),
(2,23,'Đã đi du lịch Vũng Tàu và ở rất nhiều chỗ rồi, nhưng mình vẫn ưng nhất chỗ này. Sạch sẽ, thơm. Bạn chủ rất rất nhiệt tình. 10đ'),
(2,14,'Có thể nói là 1 trong những điểm nổi bật tại vũng tàu, chủ nhà rất nhiệt tình và thân thiện. Nhất định sẽ ghé lại lần tới'),
(2,16,'Phòng sạch sẽ, bạn hướng dẫn nhận phòng nhiệt tình dễ thương. Không nghĩ giá như vậy lại có thể tìm được 1 căn hộ tốt như này');


INSERT INTO hotel_reviews (hotel_id,user_id,comment)
VALUES
(3,26,'bữa sáng ok, có nhiều món để chọn, dù trễ hơn 9h vẫn được phục vụ
Khách sạn sạch sẽ, gần biển, nhân viên nhiệt tình, lịch sử, tiện nghi, có xuất...'),
(3,25,'Vị trí sát biển thuận tiện tắm biển và dạo quanh biển
Ăn sáng tuy không nhiều nhưng chất lượng, hợp khẩu vị
Nhân viên thân thiện, hỗ trợ khách...'),
(3,24,'sát biển, tiện lợi, sạch sẽ, nhân viên nhiệt tình 10/10'),
(3,23,'Mình thấy view từ căn phòng mình thuê rất đẹp
Nhân viên lễ tân rất vui vẻ và tận tình'),
(3,14,'Ks rất sạch sẽ
Tiện nghi
Nhân viên vv.nhiet tình
Vị trí rất thuận tiện'),
(3,16,'Vị trí đẹp đối diện biển, thuận tiện di chuyển, nhân viên hỗ trợ tốt cho mình về quán ăn, địa điểm làm đẹp');


INSERT INTO hotel_reviews (hotel_id,user_id,comment)
VALUES
(4,2,'Chỗ ở rất sạch sẽ, phòng rộng rãi thoáng, bữa sáng ổn. Bãi biển sát khách sạn( bãi biển nước khá dơ, nhiều hố). Hồ bơi hơi nhỏ. Chỗ ở hơi xa trung tâm, tiện cho gia đình có xe riêng. Khách sạn có cho thuê xe máy.'),
(4,4,'Đây là nơi gần như tuyệt nhất trong những lần mình đến với Vũng Tàu. Mọi thứ đều rất tốt, chỉ hơi tiếc là đồ ăn sáng chưa hợp khẩu vị của team mình lắm'),
(4,2,'Khách sạn sạch sẽ rộng rãi, thoải mái, bữa sáng nhiều món'),
(4,11,'View rất đẹp và thoáng, rất gần biển. Hồ bơi sạch sẽ. Nhân viên thân thiện'),
(4,9,'Khách sạn sạch sẽ, tiện nghi. Đáp ứng mọi nhu cầu. Phòng mình có 2 ban công, một hướng biển rất thoáng, một hướng về khu xây dựng thì nhìn chán'),
(4,10,'Phòng rất thoải mái và nhân viên rất thân thiện Hoàn hảo');


INSERT INTO hotel_reviews (hotel_id,user_id,comment)
VALUES
(5,11,'Decor đẹp gì đâu, 10 điểm cho chủ. Mong lần sau được ở phòng decor như này tiếp'),
(5,10,'Mình book ở đây 2 ngày thì thấy rất thích và thoải mái, chỗ ở rất sạch sẽ và tiện nghi, decor phòng cũng rất dễ thương lun, sống ảo hơi bị đẹp, khu vực chung cư có GS25 và các cửa hàng bán đồ ăn, có dịch vụ gọi đồ ăn lên tận phòng, studio mình'),
(5,9,'Các bạn nhân viên siêu dễ thương. Phòng sạch sẽ và rất thoáng. Nếu đi VT mình sẽ tiếp tục ở đây'),
(5,24,'Tự nấu ăn và giặt đồ máy giặt cửa trước rất tiện lợi, đủ tiện nghi phù hợp du lịch giải trí'),
(5,16,'Sảnh đẹp, thang máy không kẹt như nhiều người nói. Wifi có mọi nơi trong tòa nhà'),
(5,23,'Hồ bơi đẹp, tiện ích nội khu đầy đủ phù hợp căn hộ 4 sao. Giá cả phải chăng. nhân viên checkin thì thân thiện. nếu có dịp sẽ quay lại');


INSERT INTO hotel_reviews (hotel_id,user_id,comment)
VALUES
(6,13,'Phòng đẩy đủ tiện nghi, thích hợp cho gia đinh nghỉ dưỡng. Biệt thự có hồ bơi riêng, tự do nấu ăn. Bể bơi lớn tắm nước nóng và bãi biền đẹp.'),
(6,14,'Villa không gian rộng rãi, gần hồ bơi chính. Nhân viên thân thiện và hỗ trợ nhiệt tình. Bãi biển tại resort đẹp, mấy đứa trẻ rất thích.'),
(6,15,'Đây là nơi tôi đã dày công tìm tòi trong chuyến đi đầu tiên đến Việt Nam. Chỗ ở này đã được chọn cho một chuyến đi lớn của gia đình.'),
(6,21,'Chúng tôi xin chân thành cảm ơn Jenny vì sự chuẩn bị chu đáo và hỗ trợ của cô ấy từ khâu nhận phòng đến trả phòng.'),
(6,27,'Vince, người đã giúp tôi đi lại thoải mái ở Đà Nẵng trong lần đầu tiên tôi đến thăm. '),
(6,30,'Tôi đã nghỉ ngơi và nghỉ ngơi tốt. Tôi sẽ đi lần nữa');



INSERT INTO hotel_reviews (hotel_id,user_id,comment)
VALUES
(7,5,'Cảm ơn chị quản lí đã hỗ trợ em. Phòng sạch sẽ, trang bị đầy đủ. Rất là ưng ý ạ.'),
(7,6,'Chị chủ rất nhiệt tình, thân thiện, với mức giá này thì vượt quá mong đợi của tôi.'),
(7,7,'Chủ khách sạn rất tốt bụng và nhiệt tình. Phòng sạch sẽ thoáng mát. bọn mình rất hài lòng cho kì nghỉ '),
(7,8,'Khách sạn có đầy đủ tiện nghi mà mình cần, chị chủ cũng rất nhiệt tình và dễ thương, khách sạn sạch sẽ, mỗi phòng một Wifi nên rất tiện lợi cho mình làm việc'),
(7,9,'Phòng sạch sẽ, đầy đủ tiện nghỉ, em chủ nhà thì cực kỳ mến khách , lần sau sẽ quay lại đây'),
(7,30,'Mọi thứ, nhân viên dễ thương, phòng tuyệt vời, tất cả các thiết bị nấu ăn được cung cấp, khu vực tiếp khách riêng biệt');


INSERT INTO hotel_reviews (hotel_id,user_id,comment)
VALUES
(8,5,'Cảm ơn chị quản lí đã hỗ trợ em. Phòng sạch sẽ, trang bị đầy đủ. Rất là ưng ý ạ.'),
(8,8,'Chị chủ rất nhiệt tình, thân thiện, với mức giá này thì vượt quá mong đợi của tôi.'),
(8,1,'Chủ khách sạn rất tốt bụng và nhiệt tình. Phòng sạch sẽ thoáng mát. bọn mình rất hài lòng cho kì nghỉ '),
(8,3,'Khách sạn có đầy đủ tiện nghi mà mình cần, chị chủ cũng rất nhiệt tình và dễ thương, khách sạn sạch sẽ, mỗi phòng một Wifi nên rất tiện lợi cho mình làm việc'),
(8,25,'Phòng sạch sẽ, đầy đủ tiện nghỉ, em chủ nhà thì cực kỳ mến khách , lần sau sẽ quay lại đây'),
(8,29,'Mọi thứ, nhân viên dễ thương, phòng tuyệt vời, tất cả các thiết bị nấu ăn được cung cấp, khu vực tiếp khách riêng biệt');


INSERT INTO hotel_reviews (hotel_id,user_id,comment)
VALUES
(9,5,'Cảm ơn chị quản lí đã hỗ trợ em. Phòng sạch sẽ, trang bị đầy đủ. Rất là ưng ý ạ.'),
(9,22,'Chị chủ rất nhiệt tình, thân thiện, với mức giá này thì vượt quá mong đợi của tôi.'),
(9,27,'Chủ khách sạn rất tốt bụng và nhiệt tình. Phòng sạch sẽ thoáng mát. bọn mình rất hài lòng cho kì nghỉ '),
(9,18,'Khách sạn có đầy đủ tiện nghi mà mình cần, chị chủ cũng rất nhiệt tình và dễ thương, khách sạn sạch sẽ, mỗi phòng một Wifi nên rất tiện lợi cho mình làm việc'),
(9,10,'Phòng sạch sẽ, đầy đủ tiện nghỉ, em chủ nhà thì cực kỳ mến khách , lần sau sẽ quay lại đây'),
(9,12,'Mọi thứ, nhân viên dễ thương, phòng tuyệt vời, tất cả các thiết bị nấu ăn được cung cấp, khu vực tiếp khách riêng biệt');


INSERT INTO hotel_reviews (hotel_id,user_id,comment)
VALUES
(10,2,'Chỗ ở rất sạch sẽ, phòng rộng rãi thoáng, bữa sáng ổn. Bãi biển sát khách sạn( bãi biển nước khá dơ, nhiều hố). Hồ bơi hơi nhỏ. Chỗ ở hơi xa trung tâm, tiện cho gia đình có xe riêng. Khách sạn có cho thuê xe máy.'),
(10,4,'Đây là nơi gần như tuyệt nhất trong những lần mình đến với Vũng Tàu. Mọi thứ đều rất tốt, chỉ hơi tiếc là đồ ăn sáng chưa hợp khẩu vị của team mình lắm'),
(10,2,'Khách sạn sạch sẽ rộng rãi, thoải mái, bữa sáng nhiều món'),
(10,11,'View rất đẹp và thoáng, rất gần biển. Hồ bơi sạch sẽ. Nhân viên thân thiện'),
(10,9,'Khách sạn sạch sẽ, tiện nghi. Đáp ứng mọi nhu cầu. Phòng mình có 2 ban công, một hướng biển rất thoáng, một hướng về khu xây dựng thì nhìn chán'),
(10,10,'Phòng rất thoải mái và nhân viên rất thân thiện Hoàn hảo');


INSERT INTO hotel_reviews (hotel_id,user_id,comment)
VALUES
(11,2,'Tiện lợi, sạch sẽ , view đẹp. Chổ đậu xe thoải mái , ăn sáng ngon'),
(11,3,'Phòng sạch sẽ, nhân viên thân thiện, vị trí gần biển.'),
(11,4,'Phòng rộng rãi, view nhìn 1 góc biển đẹp.
Buổi tối view siêu đẹp nha. Lần sau sẽ trải nghiệm phòng view biển để góc nhìn đẹp hơn. Nhân viên phục vụ tốt, phản hồi nhanh'),
(11,9,'Giá phòng rẻ so với dịp lễ, ăn sáng ngon đa dạng, gần biển'),
(11,10,'Phòng rộng view đẹp. Phòng tắm rộng. Bồn tắm còn kèm chức năng massage'),
(11,11,'View rất đẹp chổ rất sạch sẽ nhân viên tận tình dễ chịu
Mình đi som hon gio nhận phòng mà nhân viên thu xếp cho nhận som nua rât ok luon rat hai long ve cho nghỉ ak');

//-------------------------INSERT Review eplies----------------------------//

INSERT INTO review_replies (review_id,user_id,reply_text)
VALUES
(1,11,'Cám ơn'),
(3,10,'Đúng như comment'),
(6,2,'Cho mình xin thêm thông tin');

INSERT INTO review_replies (review_id,user_id,reply_text)
VALUES
(7,8,'Cám ơn bạn đã chọn hotel bên mình'),
(8,8,'Cám ơn bạn đã chọn hotel bên mình'),
(9,8,'Cám ơn bạn đã chọn hotel bên mình'),
(10,8,'Cám ơn bạn đã chọn hotel bên mình'),
(11,8,'Cám ơn bạn đã chọn hotel bên mình'),
(12,8,'Cám ơn bạn đã chọn hotel bên mình');


//-------------------------INSERT amenities -------------------------------//


INSERT INTO amenities (name,icon)
VALUES
('Căn Hộ','M3 18v3.75A2.25 2.25 0 0 0 5.25 24h4.5a.75.75 0 0 0 .75-.75v-6a.75.75 0 0 1 .75-.75h1.5a.75.75 0 0 1 .75.75v6c0 .414.336.75.75.75h4.5A2.25 2.25 0 0 0 21 21.75V18a.75.75 0 0 0-1.5 0v3.75a.75.75 0 0 1-.75.75h-4.5l.75.75v-6A2.25 2.25 0 0 0 12.75 15h-1.5A2.25 2.25 0 0 0 9 17.25v6l.75-.75h-4.5a.75.75 0 0 1-.75-.75V18A.75.75 0 0 0 3 18m-1.72-.97L11.47 6.841a.75.75 0 0 1 1.06 0l10.19 10.19a.75.75 0 1 0 1.06-1.061L13.591 5.78a2.25 2.25 0 0 0-3.182 0L.219 15.97a.75.75 0 1 0 1.061 1.06m15.97-7.28v-1.5L16.5 9h3.75l-.75-.75v5.25a.75.75 0 0 0 1.5 0V8.25a.75.75 0 0 0-.75-.75H16.5a.75.75 0 0 0-.75.75v1.5a.75.75 0 0 0 1.5 0M16.522.3l-.407.543a1.793 1.793 0 0 0 .279 2.461c.12.102.14.28.045.406l-.411.548a.75.75 0 1 0 1.2.9l.407-.543a1.793 1.793 0 0 0-.279-2.461.295.295 0 0 1-.045-.406l.411-.548a.75.75 0 1 0-1.2-.9m3.75 0-.407.543a1.793 1.793 0 0 0 .279 2.461c.12.102.14.28.045.406l-.411.548a.75.75 0 1 0 1.2.9l.407-.543a1.793 1.793 0 0 0-.279-2.461.295.295 0 0 1-.045-.406l.411-.548a.75.75 0 1 0-1.2-.9'),
('Đỗ xe trong khuôn viên miễn phí','M22.5 12c0 5.799-4.701 10.5-10.5 10.5S1.5 17.799 1.5 12 6.201 1.5 12 1.5 22.5 6.201 22.5 12m1.5 0c0-6.627-5.373-12-12-12S0 5.373 0 12s5.373 12 12 12 12-5.373 12-12m-9.75-1.5a1.5 1.5 0 0 1-1.5 1.5H10.5l.75.75v-4.5L10.5 9h2.25a1.5 1.5 0 0 1 1.5 1.5m1.5 0a3 3 0 0 0-3-3H10.5a.75.75 0 0 0-.75.75v4.5c0 .414.336.75.75.75h2.25a3 3 0 0 0 3-3m-4.5 6.75v-4.5a.75.75 0 0 0-1.5 0v4.5a.75.75 0 0 0 1.5 0'),
('WiFi miễn phí', 'M14.25 18.75a2.25 2.25 0 1 1-4.5 0 2.25 2.25 0 0 1 4.5 0m1.5 0a3.75 3.75 0 1 0-7.5 0 3.75 3.75 0 0 0 7.5 0m2.08-5.833a8.25 8.25 0 0 0-11.666 0 .75.75 0 0 0 1.06 1.06 6.75 6.75 0 0 1 9.546 0 .75.75 0 0 0 1.06-1.06m3.185-3.182c-4.979-4.98-13.051-4.98-18.03 0a.75.75 0 1 0 1.06 1.06c4.394-4.393 11.516-4.393 15.91 0a.75.75 0 1 0 1.06-1.06m2.746-3.603C17.136-.043 6.864-.043.24 6.132A.75.75 0 1 0 1.26 7.23c6.05-5.638 15.429-5.638 21.478 0a.75.75 0 0 0 1.022-1.098z'),
('Phòng không hút thuốc','M19.5 9h2.25a.75.75 0 0 1 .75.75v3a.75.75 0 0 1-.75.75h-7.5a.75.75 0 0 0 0 1.5h7.5A2.25 2.25 0 0 0 24 12.75v-3a2.25 2.25 0 0 0-2.25-2.25H19.5a.75.75 0 0 0 0 1.5M5.25 13.5h-1.5l.75.75v-6L3.75 9h7.5a.75.75 0 0 0 0-1.5h-7.5a.75.75 0 0 0-.75.75v6c0 .414.336.75.75.75h1.5a.75.75 0 0 0 0-1.5M15 12v2.25a.75.75 0 0 0 1.5 0V12a.75.75 0 0 0-1.5 0M0 8.25v6a.75.75 0 0 0 1.5 0v-6a.75.75 0 0 0-1.5 0m1.28 15.53 22.5-22.5A.75.75 0 0 0 22.72.22L.22 22.72a.75.75 0 1 0 1.06 1.06M4.5.75A2.25 2.25 0 0 1 2.25 3 2.25 2.25 0 0 0 0 5.25a.75.75 0 0 0 1.5 0 .75.75 0 0 1 .75-.75A3.75 3.75 0 0 0 6 .75a.75.75 0 0 0-1.5 0'),
('Điều hoà không khí','M11.25.75v7.5a.75.75 0 0 0 1.5 0V.75a.75.75 0 0 0-1.5 0m4.031.914-3.75 3h.938l-3.75-3a.75.75 0 1 0-.938 1.172l3.75 3a.75.75 0 0 0 .938 0l3.75-3a.75.75 0 1 0-.938-1.172M1.883 7.024l6.495 3.75a.75.75 0 0 0 .75-1.299l-6.495-3.75a.75.75 0 1 0-.75 1.3zM4.69 3.99l.723 4.748.468-.812-4.473 1.748a.75.75 0 0 0 .546 1.398l4.473-1.748a.75.75 0 0 0 .468-.812l-.723-4.748a.75.75 0 1 0-1.482.226M2.632 18.274l6.495-3.75a.75.75 0 1 0-.75-1.298l-6.495 3.75a.75.75 0 1 0 .75 1.299zm-1.224-3.948 4.473 1.748-.468-.812-.723 4.748a.75.75 0 0 0 1.482.226l.723-4.748a.75.75 0 0 0-.468-.812l-4.473-1.748a.75.75 0 0 0-.546 1.398M12.75 23.25v-7.5a.75.75 0 0 0-1.5 0v7.5a.75.75 0 0 0 1.5 0m-4.031-.914 3.75-3h-.938l3.75 3a.75.75 0 0 0 .937-1.172l-3.75-3a.75.75 0 0 0-.937 0l-3.75 3a.75.75 0 0 0 .938 1.172m13.399-5.36-6.495-3.75a.75.75 0 0 0-.75 1.298l6.495 3.75a.75.75 0 0 0 .75-1.299zm-2.807 3.034-.724-4.748-.468.812 4.473-1.748a.75.75 0 0 0-.546-1.398l-4.473 1.748a.75.75 0 0 0-.468.812l.723 4.748a.75.75 0 0 0 1.483-.226m2.057-14.285-6.495 3.75a.75.75 0 0 0 .75 1.3l6.495-3.75a.75.75 0 0 0-.75-1.3m1.224 3.95-4.473-1.749.468.812.724-4.748a.75.75 0 0 0-1.483-.226l-.723 4.748a.75.75 0 0 0 .468.812l4.473 1.748a.75.75 0 0 0 .546-1.398zM11.625 7.6 8.377 9.475a.75.75 0 0 0-.375.65v3.75a.75.75 0 0 0 .375.65l3.248 1.874a.75.75 0 0 0 .75 0l3.248-1.875a.75.75 0 0 0 .375-.649v-3.75a.75.75 0 0 0-.375-.65L12.375 7.6a.75.75 0 0 0-.75 0m.75 1.3h-.75l3.248 1.874-.375-.649v3.75l.375-.65-3.248 1.876h.75l-3.248-1.876.375.65v-3.75l-.375.65z'),
('Phòng gia đình','<M21.75 5.25a2.25 2.25 0 1 1-4.5 0 2.25 2.25 0 0 1 4.5 0m1.5 0a3.75 3.75 0 1 0-7.5 0 3.75 3.75 0 0 0 7.5 0m-6.182 15.093.188 1.5A.75.75 0 0 0 18 22.5h3a.75.75 0 0 0 .744-.657l.75-6-.744.657h1.5a.75.75 0 0 0 .75-.75V13.5a4.5 4.5 0 0 0-7.2-3.6.75.75 0 1 0 .9 1.2 3 3 0 0 1 4.8 2.4v2.25l.75-.75h-1.5a.75.75 0 0 0-.744.657l-.75 6L21 21h-3l.744.657-.188-1.5a.75.75 0 0 0-1.488.186M6.75 5.25a2.25 2.25 0 1 1-4.5 0 2.25 2.25 0 0 1 4.5 0m1.5 0a3.75 3.75 0 1 0-7.5 0 3.75 3.75 0 0 0 7.5 0M5.444 20.157l-.188 1.5L6 21H3l.744.657-.75-6A.75.75 0 0 0 2.25 15H.75l.75.75V13.5a3 3 0 0 1 4.8-2.4.75.75 0 1 0 .9-1.2A4.5 4.5 0 0 0 0 13.5v2.25c0 .414.336.75.75.75h1.5l-.744-.657.75 6A.75.75 0 0 0 3 22.5h3a.75.75 0 0 0 .744-.657l.188-1.5a.75.75 0 0 0-1.488-.186M13.5 9a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0M15 9a3 3 0 1 0-6 0 3 3 0 0 0 6 0m-3 3a4.5 4.5 0 0 0-4.5 4.5v.75c0 .414.336.75.75.75h1.5l-.74-.627.75 4.5a.75.75 0 0 0 .74.627H12a.75.75 0 0 0 0-1.5h-1.5l.74.627-.75-4.5a.75.75 0 0 0-.74-.627h-1.5l.75.75v-.75a3 3 0 0 1 3-3 .75.75 0 0 0 0-1.5m0 1.5a3 3 0 0 1 3 3v.75l.75-.75h-1.5a.75.75 0 0 0-.74.627l-.75 4.5.74-.627H12a.75.75 0 0 0 0 1.5h1.5a.75.75 0 0 0 .74-.627l.75-4.5-.74.627h1.5a.75.75 0 0 0 .75-.75v-.75A4.5 4.5 0 0 0 12 12a.75.75 0 0 0 0 1.5'),
('Dịch vụ phòng','M4.5 11.579a8.25 8.25 0 1 1 16.5 0l.75-.75h-18zm-1.5 0c0 .414.336.75.75.75h18a.75.75 0 0 0 .75-.75c0-5.385-4.365-9.75-9.75-9.75S3 6.194 3 11.579m-.75.75h21a.75.75 0 0 0 0-1.5h-21a.75.75 0 0 0 0 1.5M12 1.079v1.5a.75.75 0 0 0 1.5 0v-1.5a.75.75 0 0 0-1.5 0M1.273 22.616l3.75-3.65-1.011.032 5.25 4.5a.75.75 0 0 0 .927.039l12.062-8.711a1.92 1.92 0 0 0-.136-3.203 5.25 5.25 0 0 0-5.25-.088l-2.929 1.625a3.85 3.85 0 0 1-4.543-.646l-.587-.587a3.75 3.75 0 0 0-4.326-.698 1.606 1.606 0 0 0-.417 2.57l.459.46a3.75 3.75 0 0 1 1.02 3.384l-.371 1.842a.75.75 0 0 0 1.47.296l.372-1.843a5.25 5.25 0 0 0-1.43-4.74l-.459-.458a.106.106 0 0 1 .028-.17 2.25 2.25 0 0 1 2.595.42l.59.588a5.35 5.35 0 0 0 6.322.897l2.934-1.628a3.75 3.75 0 0 1 3.75.062.42.42 0 0 1 .03.7L9.311 22.322l.927.039-5.25-4.5a.75.75 0 0 0-1.011.032l-3.75 3.65a.75.75 0 1 0 1.046 1.074z'),
('Tiện nghi cho khách khuyết tật','M4.5 11.579a8.25 8.25 0 1 1 16.5 0l.75-.75h-18zm-1.5 0c0 .414.336.75.75.75h18a.75.75 0 0 0 .75-.75c0-5.385-4.365-9.75-9.75-9.75S3 6.194 3 11.579m-.75.75h21a.75.75 0 0 0 0-1.5h-21a.75.75 0 0 0 0 1.5M12 1.079v1.5a.75.75 0 0 0 1.5 0v-1.5a.75.75 0 0 0-1.5 0M1.273 22.616l3.75-3.65-1.011.032 5.25 4.5a.75.75 0 0 0 .927.039l12.062-8.711a1.92 1.92 0 0 0-.136-3.203 5.25 5.25 0 0 0-5.25-.088l-2.929 1.625a3.85 3.85 0 0 1-4.543-.646l-.587-.587a3.75 3.75 0 0 0-4.326-.698 1.606 1.606 0 0 0-.417 2.57l.459.46a3.75 3.75 0 0 1 1.02 3.384l-.371 1.842a.75.75 0 0 0 1.47.296l.372-1.843a5.25 5.25 0 0 0-1.43-4.74l-.459-.458a.106.106 0 0 1 .028-.17 2.25 2.25 0 0 1 2.595.42l.59.588a5.35 5.35 0 0 0 6.322.897l2.934-1.628a3.75 3.75 0 0 1 3.75.062.42.42 0 0 1 .03.7L9.311 22.322l.927.039-5.25-4.5a.75.75 0 0 0-1.011.032l-3.75 3.65a.75.75 0 1 0 1.046 1.074z'),
('Phòng tắm riêng','M15.375 10.875a1.875 1.875 0 1 1-3.75 0 1.875 1.875 0 0 1 3.75 0m1.5 0a3.375 3.375 0 1 0-6.75 0 3.375 3.375 0 0 0 6.75 0m.375 12.375V18.7l-.667.745C20.748 18.98 24 15.925 24 10.5a2.25 2.25 0 0 0-4.5 0c0 1.945-.609 3.154-1.64 3.848a3.97 3.97 0 0 1-2.132.652H9a3.75 3.75 0 1 0 0 7.5h3a2.25 2.25 0 0 0 0-4.5H9a.75.75 0 0 0 0 1.5h3a.75.75 0 0 1 0 1.5H9a2.25 2.25 0 0 1 0-4.5h6.74a5.43 5.43 0 0 0 2.957-.908C20.154 14.613 21 12.932 21 10.5a.75.75 0 0 1 1.5 0c0 4.6-2.628 7.069-6.083 7.455a.75.75 0 0 0-.667.745v4.55a.75.75 0 0 0 1.5 0m-7.5-1.5v1.5a.75.75 0 0 0 1.5 0v-1.5a.75.75 0 0 0-1.5 0M.75 1.5h1.5l-.53-.22 1.402 1.402a.75.75 0 0 0 1.06-1.06L2.78.22A.75.75 0 0 0 2.25 0H.75a.75.75 0 1 0 0 1.5m2.983 3.754a.01.01 0 0 1 .016.002c-.542-1.072-.1-2.426 1.008-2.988a2.25 2.25 0 0 1 2.037 0c-.041-.022-.043-.029-.04-.034l.002-.002-3.013 3.012zm1.07 1.05 3.002-3A1.49 1.49 0 0 0 7.51.951 3.77 3.77 0 0 0 4.079.929 3.75 3.75 0 0 0 2.43 5.971a1.49 1.49 0 0 0 2.382.323zm3.408-.968 1.116.62a.75.75 0 1 0 .728-1.312l-1.116-.62a.75.75 0 1 0-.728 1.312m1.964-2.233 1.615.44a.75.75 0 1 0 .394-1.448l-1.615-.44a.75.75 0 1 0-.394 1.448m4.217 1.15 1.615.44a.75.75 0 0 0 .396-1.447l-1.615-.44a.75.75 0 0 0-.396 1.447M5.697 7.388l.577 1.038a.75.75 0 1 0 1.312-.728L7.009 6.66a.75.75 0 1 0-1.312.728M3.284 8.94l.44 1.615a.75.75 0 1 0 1.448-.394l-.44-1.615a.75.75 0 1 0-1.448.394m1.15 4.219.246.896a.75.75 0 1 0 1.446-.396l-.245-.896a.75.75 0 1 0-1.446.396z'),
('Căn Nhà','M3 18v3.75A2.25 2.25 0 0 0 5.25 24h4.5a.75.75 0 0 0 .75-.75v-6a.75.75 0 0 1 .75-.75h1.5a.75.75 0 0 1 .75.75v6c0 .414.336.75.75.75h4.5A2.25 2.25 0 0 0 21 21.75V18a.75.75 0 0 0-1.5 0v3.75a.75.75 0 0 1-.75.75h-4.5l.75.75v-6A2.25 2.25 0 0 0 12.75 15h-1.5A2.25 2.25 0 0 0 9 17.25v6l.75-.75h-4.5a.75.75 0 0 1-.75-.75V18A.75.75 0 0 0 3 18m-1.72-.97L11.47 6.841a.75.75 0 0 1 1.06 0l10.19 10.19a.75.75 0 1 0 1.06-1.061L13.591 5.78a2.25 2.25 0 0 0-3.182 0L.219 15.97a.75.75 0 1 0 1.061 1.06m15.97-7.28v-1.5L16.5 9h3.75l-.75-.75v5.25a.75.75 0 0 0 1.5 0V8.25a.75.75 0 0 0-.75-.75H16.5a.75.75 0 0 0-.75.75v1.5a.75.75 0 0 0 1.5 0M16.522.3l-.407.543a1.793 1.793 0 0 0 .279 2.461c.12.102.14.28.045.406l-.411.548a.75.75 0 1 0 1.2.9l.407-.543a1.793 1.793 0 0 0-.279-2.461.295.295 0 0 1-.045-.406l.411-.548a.75.75 0 1 0-1.2-.9m3.75 0-.407.543a1.793 1.793 0 0 0 .279 2.461c.12.102.14.28.045.406l-.411.548a.75.75 0 1 0 1.2.9l.407-.543a1.793 1.793 0 0 0-.279-2.461.295.295 0 0 1-.045-.406l.411-.548a.75.75 0 1 0-1.2-.9'),
('Cho phép mang theo thú cưng','M16.01 15a4.5 4.5 0 1 0-9 0l.75-.75a3.75 3.75 0 1 0 0 7.5 4.96 4.96 0 0 0 2.245-.59 3.28 3.28 0 0 1 3.018.005c.677.365 1.44.567 2.22.585a3.75 3.75 0 1 0 .018-7.5zm-1.5 0c0 .414.336.75.75.75a2.25 2.25 0 0 1 0 4.5 3.4 3.4 0 0 1-1.536-.41 4.79 4.79 0 0 0-4.42-.005 3.46 3.46 0 0 1-1.562.415A2.247 2.247 0 0 1 5.51 18a2.25 2.25 0 0 1 2.25-2.25.75.75 0 0 0 .75-.75 3 3 0 1 1 6 0m-9.75-3.75a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m1.5 0a3 3 0 1 0-6 0 3 3 0 0 0 6 0m3-6a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m1.5 0a3 3 0 1 0-6 0 3 3 0 0 0 6 0m6 0a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m1.5 0a3 3 0 1 0-6 0 3 3 0 0 0 6 0m3.75 6a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m1.5 0a3 3 0 1 0-6 0 3 3 0 0 0 6 0'),
('Tiện nghi BBQ','M17.633 9.75a.384.384 0 0 1 .38.457c-.726 3.298-4.007 5.394-7.324 4.664a6.15 6.15 0 0 1-4.684-4.683.38.38 0 0 1 .384-.438h11.236zm-.008-1.5H6.4a1.882 1.882 0 0 0-1.863 2.243 7.65 7.65 0 0 0 14.945.018 1.884 1.884 0 0 0-1.865-2.26zm2.58 14.742-3.268-8.923a.75.75 0 0 0-1.408.516l3.267 8.923a.75.75 0 0 0 1.408-.516zm-15 .516 3.266-8.923a.75.75 0 0 0-1.408-.516l-3.267 8.923a.75.75 0 0 0 1.408.516zM18.401 19.5H5.598a.75.75 0 0 0 0 1.5h12.804a.75.75 0 0 0 0-1.5zM6.321.299l-.62.823a2.333 2.333 0 0 0 .364 3.204.835.835 0 0 1 .126 1.146l-.166.221a.75.75 0 1 0 1.2.9l.162-.216c.754-.987.597-2.4-.36-3.202a.834.834 0 0 1-.13-1.147l.622-.827A.75.75 0 0 0 6.321.3zM11.956.3l-.617.823c-.754.987-.597 2.4.36 3.202.344.287.4.792.129 1.147l-.166.221a.75.75 0 1 0 1.2.9l.162-.216a2.33 2.33 0 0 0-.362-3.202.835.835 0 0 1-.127-1.147l.621-.828a.75.75 0 1 0-1.2-.9m5.25 0-.617.823c-.754.987-.597 2.4.36 3.202.344.287.4.792.129 1.147l-.166.221a.75.75 0 1 0 1.2.9l.162-.216a2.33 2.33 0 0 0-.362-3.202.835.835 0 0 1-.127-1.147l.621-.828a.75.75 0 1 0-1.2-.9'),
('Ban công','M17.633 9.75a.384.384 0 0 1 .38.457c-.726 3.298-4.007 5.394-7.324 4.664a6.15 6.15 0 0 1-4.684-4.683.38.38 0 0 1 .384-.438h11.236zm-.008-1.5H6.4a1.882 1.882 0 0 0-1.863 2.243 7.65 7.65 0 0 0 14.945.018 1.884 1.884 0 0 0-1.865-2.26zm2.58 14.742-3.268-8.923a.75.75 0 0 0-1.408.516l3.267 8.923a.75.75 0 0 0 1.408-.516zm-15 .516 3.266-8.923a.75.75 0 0 0-1.408-.516l-3.267 8.923a.75.75 0 0 0 1.408.516zM18.401 19.5H5.598a.75.75 0 0 0 0 1.5h12.804a.75.75 0 0 0 0-1.5zM6.321.299l-.62.823a2.333 2.333 0 0 0 .364 3.204.835.835 0 0 1 .126 1.146l-.166.221a.75.75 0 1 0 1.2.9l.162-.216c.754-.987.597-2.4-.36-3.202a.834.834 0 0 1-.13-1.147l.622-.827A.75.75 0 0 0 6.321.3zM11.956.3l-.617.823c-.754.987-.597 2.4.36 3.202.344.287.4.792.129 1.147l-.166.221a.75.75 0 1 0 1.2.9l.162-.216a2.33 2.33 0 0 0-.362-3.202.835.835 0 0 1-.127-1.147l.621-.828a.75.75 0 1 0-1.2-.9m5.25 0-.617.823c-.754.987-.597 2.4.36 3.202.344.287.4.792.129 1.147l-.166.221a.75.75 0 1 0 1.2.9l.162-.216a2.33 2.33 0 0 0-.362-3.202.835.835 0 0 1-.127-1.147l.621-.828a.75.75 0 1 0-1.2-.9'),
('Tầm nhìn ra khung cảnh','M12.013 4.501c-3.88-.065-8.202 2.372-11.39 5.88a2.414 2.414 0 0 0-.001 3.232c3.183 3.506 7.481 5.95 11.39 5.885 3.885.066 8.183-2.378 11.367-5.883.83-.92.83-2.314.002-3.232-3.194-3.512-7.515-5.947-11.394-5.882zm0 1.5c3.378-.057 7.328 2.17 10.256 5.389.31.344.31.872-.002 1.219-2.92 3.213-6.848 5.446-10.254 5.39-3.432.056-7.36-2.178-10.279-5.392a.91.91 0 0 1 .002-1.22c2.922-3.216 6.872-5.443 10.251-5.386zM15 12a3 3 0 1 1-6 .004 3 3 0 0 1 6-.007zm1.5 0v-.003a4.5 4.5 0 1 0-9-.002 4.5 4.5 0 0 0 9 .005'),
('Nhìn ra hồ bơi','M23.097 21.71c-.896.187-1.71-.114-2.442-.76a4.629 4.629 0 0 1-.74-.837.75.75 0 0 0-1.272-.004 3.557 3.557 0 0 1-2.925 1.661 2.98 2.98 0 0 1-2.559-1.608.75.75 0 0 0-1.26-.11 4.418 4.418 0 0 1-3.286 1.719c-1.121-.058-2.216-.68-2.876-1.677a.75.75 0 0 0-1.214-.05 6.17 6.17 0 0 1-1.125 1.033c-.833.588-1.677.85-2.49.675a.75.75 0 1 0-.315 1.466c1.285.276 2.526-.107 3.67-.915a7.084 7.084 0 0 0 1.438-1.33l-1.214-.05a5.257 5.257 0 0 0 4.126 2.346c1.807-.084 3.417-.926 4.476-2.303l-1.26-.11a4.49 4.49 0 0 0 3.892 2.414 5.07 5.07 0 0 0 4.192-2.361l-1.272-.004c.192.308.533.739 1.022 1.17 1.057.931 2.32 1.4 3.74 1.104a.75.75 0 0 0-.306-1.468zm0-4.5c-.896.187-1.71-.114-2.442-.76a4.629 4.629 0 0 1-.74-.837.75.75 0 0 0-1.272-.004 3.557 3.557 0 0 1-2.925 1.661 2.98 2.98 0 0 1-2.559-1.608.75.75 0 0 0-1.26-.11 4.418 4.418 0 0 1-3.286 1.719c-1.121-.058-2.216-.68-2.876-1.677a.75.75 0 0 0-1.214-.05 6.17 6.17 0 0 1-1.125 1.033c-.833.588-1.677.85-2.49.675a.75.75 0 1 0-.315 1.466c1.285.276 2.526-.107 3.67-.915a7.084 7.084 0 0 0 1.438-1.33l-1.214-.05a5.257 5.257 0 0 0 4.126 2.346c1.807-.084 3.417-.926 4.476-2.303l-1.26-.11a4.49 4.49 0 0 0 3.892 2.414 5.07 5.07 0 0 0 4.192-2.361l-1.272-.004c.192.308.533.739 1.022 1.17 1.057.931 2.32 1.4 3.74 1.104a.75.75 0 0 0-.306-1.468zm-1.722-8.64a1.875 1.875 0 1 1-3.75 0 1.875 1.875 0 0 1 3.75 0zm1.5 0a3.375 3.375 0 1 0-6.75 0 3.375 3.375 0 0 0 6.75 0zM7.777 14.636l3.831-2.4a.75.75 0 0 0 .166-1.13L8.964 7.9a2.25 2.25 0 0 1 .687-3.494l4.264-2.135a.751.751 0 1 1 .686 1.333l-.01.006-3.405 1.702a1.502 1.502 0 0 0-.448 2.334l5.375 6.142a.75.75 0 1 0 1.128-.988l-5.377-6.145-.002-.003v-.001l3.394-1.697.027-.013A2.25 2.25 0 0 0 13.238.93L8.98 3.065a3.749 3.749 0 0 0-1.144 5.824l2.81 3.206.166-1.13-3.831 2.4a.75.75 0 0 0 .796 1.272z'),
('Hồ bơi có tầm nhìn','M23.097 21.71c-.896.187-1.71-.114-2.442-.76a4.629 4.629 0 0 1-.74-.837.75.75 0 0 0-1.272-.004 3.557 3.557 0 0 1-2.925 1.661 2.98 2.98 0 0 1-2.559-1.608.75.75 0 0 0-1.26-.11 4.418 4.418 0 0 1-3.286 1.719c-1.121-.058-2.216-.68-2.876-1.677a.75.75 0 0 0-1.214-.05 6.17 6.17 0 0 1-1.125 1.033c-.833.588-1.677.85-2.49.675a.75.75 0 1 0-.315 1.466c1.285.276 2.526-.107 3.67-.915a7.084 7.084 0 0 0 1.438-1.33l-1.214-.05a5.257 5.257 0 0 0 4.126 2.346c1.807-.084 3.417-.926 4.476-2.303l-1.26-.11a4.49 4.49 0 0 0 3.892 2.414 5.07 5.07 0 0 0 4.192-2.361l-1.272-.004c.192.308.533.739 1.022 1.17 1.057.931 2.32 1.4 3.74 1.104a.75.75 0 0 0-.306-1.468zm0-4.5c-.896.187-1.71-.114-2.442-.76a4.629 4.629 0 0 1-.74-.837.75.75 0 0 0-1.272-.004 3.557 3.557 0 0 1-2.925 1.661 2.98 2.98 0 0 1-2.559-1.608.75.75 0 0 0-1.26-.11 4.418 4.418 0 0 1-3.286 1.719c-1.121-.058-2.216-.68-2.876-1.677a.75.75 0 0 0-1.214-.05 6.17 6.17 0 0 1-1.125 1.033c-.833.588-1.677.85-2.49.675a.75.75 0 1 0-.315 1.466c1.285.276 2.526-.107 3.67-.915a7.084 7.084 0 0 0 1.438-1.33l-1.214-.05a5.257 5.257 0 0 0 4.126 2.346c1.807-.084 3.417-.926 4.476-2.303l-1.26-.11a4.49 4.49 0 0 0 3.892 2.414 5.07 5.07 0 0 0 4.192-2.361l-1.272-.004c.192.308.533.739 1.022 1.17 1.057.931 2.32 1.4 3.74 1.104a.75.75 0 0 0-.306-1.468zm-1.722-8.64a1.875 1.875 0 1 1-3.75 0 1.875 1.875 0 0 1 3.75 0zm1.5 0a3.375 3.375 0 1 0-6.75 0 3.375 3.375 0 0 0 6.75 0zM7.777 14.636l3.831-2.4a.75.75 0 0 0 .166-1.13L8.964 7.9a2.25 2.25 0 0 1 .687-3.494l4.264-2.135a.751.751 0 1 1 .686 1.333l-.01.006-3.405 1.702a1.502 1.502 0 0 0-.448 2.334l5.375 6.142a.75.75 0 1 0 1.128-.988l-5.377-6.145-.002-.003v-.001l3.394-1.697.027-.013A2.25 2.25 0 0 0 13.238.93L8.98 3.065a3.749 3.749 0 0 0-1.144 5.824l2.81 3.206.166-1.13-3.831 2.4a.75.75 0 0 0 .796 1.272z'),
('Hồ bơi sân thượng','M23.097 21.71c-.896.187-1.71-.114-2.442-.76a4.629 4.629 0 0 1-.74-.837.75.75 0 0 0-1.272-.004 3.557 3.557 0 0 1-2.925 1.661 2.98 2.98 0 0 1-2.559-1.608.75.75 0 0 0-1.26-.11 4.418 4.418 0 0 1-3.286 1.719c-1.121-.058-2.216-.68-2.876-1.677a.75.75 0 0 0-1.214-.05 6.17 6.17 0 0 1-1.125 1.033c-.833.588-1.677.85-2.49.675a.75.75 0 1 0-.315 1.466c1.285.276 2.526-.107 3.67-.915a7.084 7.084 0 0 0 1.438-1.33l-1.214-.05a5.257 5.257 0 0 0 4.126 2.346c1.807-.084 3.417-.926 4.476-2.303l-1.26-.11a4.49 4.49 0 0 0 3.892 2.414 5.07 5.07 0 0 0 4.192-2.361l-1.272-.004c.192.308.533.739 1.022 1.17 1.057.931 2.32 1.4 3.74 1.104a.75.75 0 0 0-.306-1.468zm0-4.5c-.896.187-1.71-.114-2.442-.76a4.629 4.629 0 0 1-.74-.837.75.75 0 0 0-1.272-.004 3.557 3.557 0 0 1-2.925 1.661 2.98 2.98 0 0 1-2.559-1.608.75.75 0 0 0-1.26-.11 4.418 4.418 0 0 1-3.286 1.719c-1.121-.058-2.216-.68-2.876-1.677a.75.75 0 0 0-1.214-.05 6.17 6.17 0 0 1-1.125 1.033c-.833.588-1.677.85-2.49.675a.75.75 0 1 0-.315 1.466c1.285.276 2.526-.107 3.67-.915a7.084 7.084 0 0 0 1.438-1.33l-1.214-.05a5.257 5.257 0 0 0 4.126 2.346c1.807-.084 3.417-.926 4.476-2.303l-1.26-.11a4.49 4.49 0 0 0 3.892 2.414 5.07 5.07 0 0 0 4.192-2.361l-1.272-.004c.192.308.533.739 1.022 1.17 1.057.931 2.32 1.4 3.74 1.104a.75.75 0 0 0-.306-1.468zm-1.722-8.64a1.875 1.875 0 1 1-3.75 0 1.875 1.875 0 0 1 3.75 0zm1.5 0a3.375 3.375 0 1 0-6.75 0 3.375 3.375 0 0 0 6.75 0zM7.777 14.636l3.831-2.4a.75.75 0 0 0 .166-1.13L8.964 7.9a2.25 2.25 0 0 1 .687-3.494l4.264-2.135a.751.751 0 1 1 .686 1.333l-.01.006-3.405 1.702a1.502 1.502 0 0 0-.448 2.334l5.375 6.142a.75.75 0 1 0 1.128-.988l-5.377-6.145-.002-.003v-.001l3.394-1.697.027-.013A2.25 2.25 0 0 0 13.238.93L8.98 3.065a3.749 3.749 0 0 0-1.144 5.824l2.81 3.206.166-1.13-3.831 2.4a.75.75 0 0 0 .796 1.272z'),
('Phòng xông hơi','M.722 21.003h7.5a.75.75 0 0 0 0-1.5h-7.5a.75.75 0 0 0 0 1.5zm13.504-3.37l3.1 5.272a2.25 2.25 0 0 0 3.88-2.278l-.023-.038-3.529-5.989a2.252 2.252 0 0 0-1.932-1.097h-5.073l.67.415-2.584-5.171a2.25 2.25 0 0 0-1.81-1.235 2.228 2.228 0 0 0-2.007.897L.422 14.404a2.25 2.25 0 0 0 3.6 2.7l3.044-4.054-1.27-.114 1.913 3.824a2.25 2.25 0 0 0 2.013 1.243h1.5l-.75-.75v4.5a2.25 2.25 0 0 0 4.5 0v-4.5h-.75v.75h.65v-.75l-.646.38zm1.292-.76a.75.75 0 0 0-.646-.37h-.65a.75.75 0 0 0-.75.75v4.5a.75.75 0 0 1-1.5 0v-4.5a.75.75 0 0 0-.75-.75h-1.5a.75.75 0 0 1-.671-.415l-1.914-3.824a.75.75 0 0 0-1.27-.114l-3.045 4.054a.75.75 0 1 1-1.2-.9l4.5-6a.733.733 0 0 1 .662-.299c.265.024.493.18.61.412l2.584 5.171a.75.75 0 0 0 .671.415h5.073a.75.75 0 0 1 .643.364l3.533 5.995c.01.018.01.018.015.024a.75.75 0 0 1-1.294.76l-3.1-5.273zM9.348 3.752a1.875 1.875 0 1 1-3.75 0 1.875 1.875 0 0 1 3.75 0zm1.5 0a3.375 3.375 0 1 0-6.75 0 3.375 3.375 0 0 0 6.75 0zm10.91-2.667c.418.836.367 1.099-.104 1.72l-.092.121c-.822 1.09-.849 2.036.267 3.524a.75.75 0 0 0 1.2-.9c-.705-.94-.699-1.152-.27-1.72l.09-.119c.789-1.039.953-1.892.25-3.297a.75.75 0 0 0-1.34.671zm-3.75 0c.418.836.367 1.099-.104 1.72l-.092.121c-.822 1.09-.849 2.036.267 3.524a.75.75 0 0 0 1.2-.9c-.705-.94-.699-1.152-.27-1.72l.09-.119c.789-1.039.953-1.892.25-3.297a.75.75 0 0 0-1.34.671zm-3.75 0c.418.836.367 1.099-.104 1.72l-.092.121c-.822 1.09-.849 2.036.267 3.524a.75.75 0 0 0 1.2-.9c-.705-.94-.699-1.152-.27-1.72l.09-.119c.789-1.039.953-1.892.25-3.297a.75.75 0 0 0-1.34.671z'),
('Nhìn ra thành phố','M2.75 6h9.5a.25.25 0 0 1-.25-.25v17.5l.75-.75H2.25l.75.75V5.75a.25.25 0 0 1-.25.25zm0-1.5c-.69 0-1.25.56-1.25 1.25v17.5c0 .414.336.75.75.75h10.5a.75.75 0 0 0 .75-.75V5.75c0-.69-.56-1.25-1.25-1.25h-9.5zm3-1.5h3.5A.25.25 0 0 1 9 2.75v2.5l.75-.75h-4.5l.75.75v-2.5a.25.25 0 0 1-.25.25zm0-1.5c-.69 0-1.25.56-1.25 1.25v2.5c0 .414.336.75.75.75h4.5a.75.75 0 0 0 .75-.75v-2.5c0-.69-.56-1.25-1.25-1.25h-3.5zM5.25 9h4.5a.75.75 0 0 0 0-1.5h-4.5a.75.75 0 0 0 0 1.5zm0 3h4.5a.75.75 0 0 0 0-1.5h-4.5a.75.75 0 0 0 0 1.5zm0 3h4.5a.75.75 0 0 0 0-1.5h-4.5a.75.75 0 0 0 0 1.5zm0 3h4.5a.75.75 0 0 0 0-1.5h-4.5a.75.75 0 0 0 0 1.5zm0 3h4.5a.75.75 0 0 0 0-1.5h-4.5a.75.75 0 0 0 0 1.5zM7.5.75v1.5a.75.75 0 0 0 1.5 0V.75a.75.75 0 0 0-1.5 0zM15.75 24h6a.75.75 0 0 0 .75-.75V10.5A1.5 1.5 0 0 0 21 9h-4.5a1.5 1.5 0 0 0-1.5 1.5v12.75a.75.75 0 0 0 1.5 0V10.5H21v12.75l.75-.75h-6a.75.75 0 0 0 0 1.5zM19.5 8.25v1.5a.75.75 0 0 0 1.5 0v-1.5a.75.75 0 0 0-1.5 0zM.75 24h22.5a.75.75 0 0 0 0-1.5H.75a.75.75 0 0 0 0 1.5z'),
('TV màn hình phẳng','M22.5 10.375v6.5a.25.25 0 0 1-.25.25H1.75a.25.25 0 0 1-.25-.25v-13a.25.25 0 0 1 .25-.25h20.5a.25.25 0 0 1 .25.25v6.5zm1.5 0v-6.5a1.75 1.75 0 0 0-1.75-1.75H1.75A1.75 1.75 0 0 0 0 3.875v13c0 .966.784 1.75 1.75 1.75h20.5a1.75 1.75 0 0 0 1.75-1.75v-6.5zm-16.5 12h9a.75.75 0 0 0 0-1.5h-9a.75.75 0 0 0 0 1.5zm3.75-4.5v3.75a.75.75 0 0 0 1.5 0v-3.75a.75.75 0 0 0-1.5 0z');


//----------------------------------INSERT hotel - amenities -------------------------------//

INSERT INTO hotel_amenities (hotel_id,amenity_id)
VALUES
(1,15),
(1,16),
(1,17),
(1,18),
(1,19),
(1,20),
(1,1),
(1,2),
(1,3);

INSERT INTO hotel_amenities (hotel_id,amenity_id)
VALUES
(2,15),
(2,16),
(2,17),
(2,18),
(2,19),
(2,20),
(2,1),
(2,2),
(2,3),
(2,9),
(2,10),
(2,11);

INSERT INTO hotel_amenities (hotel_id,amenity_id)
VALUES
(3,11),
(3,16),
(3,17),
(3,18),
(3,19),
(3,20),
(3,1),
(3,2),
(3,3),
(3,4),
(3,8),
(3,9);


INSERT INTO hotel_amenities (hotel_id,amenity_id)
VALUES
(4,15),
(4,16),
(4,17),
(4,18),
(4,19),
(4,20),
(4,1),
(4,2);



INSERT INTO hotel_amenities (hotel_id,amenity_id)
VALUES
(5,15),
(5,16),
(5,17),
(5,18),
(5,19),
(5,20),
(5,1),
(5,2);



INSERT INTO hotel_amenities (hotel_id,amenity_id)
VALUES
(6,15),
(6,16),
(6,17),
(6,18),
(6,19),
(6,20),
(6,1),
(6,2);


INSERT INTO hotel_amenities (hotel_id,amenity_id)
VALUES
(7,15),
(7,16),
(7,17),
(7,18),
(7,19),
(7,20),
(7,1),
(7,2);


INSERT INTO hotel_amenities (hotel_id,amenity_id)
VALUES
(8,15),
(8,16),
(8,17),
(8,18),
(8,19),
(8,20),
(8,1),
(8,2);

INSERT INTO hotel_amenities (hotel_id,amenity_id)
VALUES
(9,15),
(9,16),
(9,17),
(9,18),
(9,19),
(9,20),
(9,1),
(9,2);


INSERT INTO hotel_amenities (hotel_id,amenity_id)
VALUES
(10,15),
(10,16),
(10,17),
(10,18),
(10,19),
(10,20),
(10,1),
(10,2);



INSERT INTO hotel_amenities (hotel_id,amenity_id)
VALUES
(11,15),
(11,16),
(11,17),
(11,18),
(11,19),
(11,20),
(11,1),
(11,2);


//-------------------------------------INSERT Room_Type--------------------------//

INSERT INTO room_type (name)
VALUES
('Căn hộ Studio'),
('Căn hộ 1 phòng ngủ'),
('Căn hộ 2 phòng ngủ'),
('Phòng Deluxe Giường Đôi'),
('Phòng Deluxe Gia đình'),
('Biệt thự 1 phòng ngủ'),
('Căn hộ deluxe');

// -------------------------------INSERT rooms -----------------------------//

INSERT INTO rooms (hotel_id,room_number,roomtype_id,description,price,status)
VALUES
(1,'1',4,'Phòng giường đôi này được bố trí phòng tắm riêng với bồn tắm, vòi sen, vòi xịt/chậu rửa vệ sinh và đồ vệ sinh cá nhân miễn phí. Phòng giường đôi có máy điều hòa, TV truyền hình cáp màn hình phẳng, lối vào riêng, tường cách âm, minibar cũng như tầm nhìn ra biển. Căn này được trang bị 1 giường.',2703125,'AVAILABLE'),
(1,'2',5,'Phòng gia đình này được bố trí phòng tắm riêng với buồng tắm đứng, vòi xịt/chậu rửa vệ sinh, máy sấy tóc và đồ vệ sinh cá nhân miễn phí. Phòng gia đình có máy điều hòa, TV truyền hình cáp màn hình phẳng, lối vào riêng, tường cách âm, minibar cũng như tầm nhìn ra khu vườn. Căn này được trang bị 3 giường.',10789777,'AVAILABLE')


INSERT INTO rooms (hotel_id,room_number,roomtype_id,description,price,status)
VALUES
(2,'1',1,'The pool with a view is a top feature of this studio. The fully equipped kitchen has a stovetop, a refrigerator, kitchenware and a microwave. The studio features a washing machine, a private entrance, a terrace with mountain views as well as a private bathroom boasting a shower. The unit has 1 bed.',3300000,'AVAILABLE'),
(2,'2',2,'This apartment features a pool with a view. Boasting a private entrance, this apartment includes 1 living room, 1 separate bedroom and 1 bathroom with a walk-in shower and a hairdryer. Meals can be prepared in the well-fitted kitchen, which has a stovetop, a refrigerator, kitchenware and a microwave. Featuring a terrace with mountain views, this apartment also offers a washing machine and a flat-screen TV with streaming services. The unit has 2 beds',3630000,'AVAILABLE'),
(2,'3',3,'Guests will have a special experience as this apartment features a pool with a view. Featuring a private entrance, this apartment is comprised of 1 living room, 2 separate bedrooms and 1 bathroom with a walk-in shower and a hairdryer. The fully equipped kitchen features a stovetop, a refrigerator, kitchenware and a microwave. Boasting a terrace with mountain views, this apartment also offers a washing machine and a flat-screen TV with streaming services. The unit offers 3 beds.',4000000,'AVAILABLE');

INSERT INTO rooms (hotel_id,room_number,roomtype_id,description,price,status)
VALUES
(3,'1',2,'Phòng giường đôi này được bố trí phòng tắm riêng với buồng tắm đứng,
 máy sấy tóc, dép và đồ vệ sinh cá nhân miễn phí. Phòng giường đôi có máy điều hòa,
 TV truyền hình cáp màn hình phẳng, tường cách âm, minibar và tủ để quần áo. Căn này được trang bị 1 giường.',2844156,'AVAILABLE'),
(3,'2',4,'Phòng giường đôi này được bố trí phòng tắm riêng với buồng tắm đứng,
 máy sấy tóc, dép và đồ vệ sinh cá nhân miễn phí. Phòng giường đôi có máy điều hòa, 
TV truyền hình cáp màn hình phẳng, tường cách âm, minibar, tủ để quần áo cũng như tầm nhìn ra khu vườn. Căn này được trang bị 1 giường.',3051948,'AVAILABLE');


INSERT INTO rooms (hotel_id,room_number,roomtype_id,description,price,status)
VALUES
(4,'1',4,'The double room provides air conditioning, a minibar, a terrace with garden views as well as a private bathroom featuring a shower. The unit offers 1 bed.',2525971,'AVAILABLE'),
(4,'2',5,'Phòng giường đôi rộng rãi này có máy điều hòa, minibar, sân hiên nhìn ra khu vườn cũng như phòng tắm riêng với vòi sen. Căn này được trang bị 1 giường.',3141540,'AVAILABLE');


//--------------------------INSERT room image---------------------------//

INSERT INTO room_image (room_id,image_title,image_description,image_path)
VALUES
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/345770325.webp?k=0bb7725dcdd3e9e3992ad36ab2ab10766b35f9761c80d4e8f83c8d9dde744637&o='),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/345770374.webp?k=7dc02c753b444b0d2f495a3e4901c60c94f67766fdaa7df0c2c857d16b2c0fe1&o='),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/492730633.webp?k=daef51da4c9c2665adabb5be865d5b46bd52a3f1cb6b32dfc4dd8153bbf2d02a&o='),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/345770371.webp?k=03d5d13ef81597324c32e9eafa98a916363d299b1588b8c7bf77e125bb3886bb&o='),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/345770360.webp?k=d6697670082496dd0010ba44bd137c8bbb22ba0acfc921a8b64cdba205383ad5&o='),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/345770429.webp?k=fb9bf422966b39b1dfb9db4b4a5801986da133ef36493de5fbfa358e23be4613&o='),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/492730620.webp?k=1983a110d51ba325e33b88b97f89cb6cb5b86faacd5d98a5fd56a09c8baa9fef&o='),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/345770418.webp?k=8e53f4106f11feebd1d9523f1a710fb813f085730e148dbb865964c3beed597d&o='),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/345770419.webp?k=2e8cf840983965d077b9641b6dcf75edb6caf5ac1e65103971d1ceda3460f64d&o='),
(1,'Annata Beach Hotel','Annata Beach Hotel','https://cf.bstatic.com/xdata/images/hotel/max1024x768/345770418.webp?k=8e53f4106f11feebd1d9523f1a710fb813f085730e148dbb865964c3beed597d&o=');


//------------------------INSERT room - amenities---------//

INSERT INTO room_amenities (room_id,amenity_id)
VALUES
(1,15),
(1,16),
(1,17),
(1,18),
(1,19),
(1,20),
(1,11),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6);



SELECT * FROM rooms; 
