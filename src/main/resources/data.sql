/* Used for H2 only. These will not be run since MySQL is used. */


create table admin(
	adminid varchar(50) not null,
    pwd varchar(50) not null,
    primary key(adminid)
);

create table cuisine(
	cuisineid varchar(50) not null,
    cuisinename varchar(50) not null,
    primary key(cuisineid)
);

create table users(
	userid varchar(50) not null,
    fname varchar(50),
    lname varchar(50),
    userpwd varchar(50) not null,
    primary key(userid)
);

create table products(
	productid integer not null,
	productname varchar(50),
	imagename varchar(50),
	productprice integer,
	stock integer,
	cuisineid varchar(50),
	description varchar(200),
	primary key(productid),
	foreign key(cuisineid) references cuisine(cuisineid)
);

insert into admin(adminid, pwd) values('admin1@foodbox.com', 'admin1@123');
insert into admin(adminid, pwd) values('admin2@foodbox.com', 'admin2@123');
insert into admin(adminid, pwd) values('admin3@foodbox.com', 'admin3@123');
insert into admin(adminid, pwd) values('admin4@foodbox.com', 'admin4@123');
insert into admin(adminid, pwd) values('admin5@foodbox.com', 'admin5@123');

insert into cuisine(cuisineid, cuisinename) values('FBC1', 'Indian');
insert into cuisine(cuisineid, cuisinename) values('FBC2', 'Chinese');
insert into cuisine(cuisineid, cuisinename) values('FBC3', 'Italian');
insert into cuisine(cuisineid, cuisinename) values('FBC4', 'Mexican');

insert into users(userid, fname, lname, userpwd) values('adam@foodbox.com','Adam', 'Michael', 'adam@123');
insert into users(userid, fname, lname, userpwd) values('carter@foodbox.com','Carter', 'Miles', 'carter@123');
insert into users(userid, fname, lname, userpwd) values('cindy@foodbox.com','Cindy', 'Nolan', 'cindy@123');
insert into users(userid, fname, lname, userpwd) values('jane@foodbox.com','Jane', 'Jaxon', 'jane@123');
insert into users(userid, fname, lname, userpwd) values('mason@foodbox.com','Mason', 'James', 'mason@123');

insert into products(productid, productname, imagename, productprice, stock, cuisineid, description) values(1,'Dosa', 'dosa.jpg', 50, 200, 'FBC1', 'A dosa is a thin pancake or crepe originating from South India, made from a fermented batter.');
insert into products(productid, productname, imagename, productprice, stock, cuisineid, description) values(2,'Idli', 'idli.jpg', 25, 200, 'FBC1', 'Idli or idly are a type of savoury rice cake, originating from the Indian subcontinent, popular as breakfast.');
insert into products(productid, productname, imagename, productprice, stock, cuisineid, description) values(3,'Biryani', 'biryani.jpg', 250, 200, 'FBC1', 'Biryani is a mixed rice dish originating among the people of the Indian subcontinent. It is made by adding rice and spices to meat.');
insert into products(productid, productname, imagename, productprice, stock, cuisineid, description) values(4,'Chapati', 'chapati.jpg', 20, 200, 'FBC1', 'Chapati is an unleavened flatbread originating from the Indian subcontinent and staple in India.');
insert into products(productid, productname, imagename, productprice, stock, cuisineid, description) values(5,'Dim Sum', 'dimsum.jpg', 200, 200, 'FBC2', 'Dim sum is a large range of small Chinese dishes that are traditionally enjoyed in restaurants for brunch.');
insert into products(productid, productname, imagename, productprice, stock, cuisineid, description) values(6,'Hot and Sour Soup', 'soup.jpg', 75, 200, 'FBC2', 'Hot and sour soup is a soup from Asian culinary traditions.');
insert into products(productid, productname, imagename, productprice, stock, cuisineid, description) values(7,'Quick Noodles', 'quicknoodles.jpg', 100, 200, 'FBC2', 'Noodles are a type of food made from unleavened dough which is rolled flat and cut, stretched or extruded, into long strips or strings.');
insert into products(productid, productname, imagename, productprice, stock, cuisineid, description) values(8,'Szechwan Chilli Chicken', 'szechwanchicken.jpg', 275, 200, 'FBC2', 'Szechuan Chicken is a stir fry of crispy chicken thigh meat mixed with dried red chili peppers.');
insert into products(productid, productname, imagename, productprice, stock, cuisineid, description) values(9,'Margherita Pizza', 'margheritapizza.jpg', 300, 200, 'FBC3', 'Pizza margherita, as the Italians call it, is a simple pizza hailing from Naples.');
insert into products(productid, productname, imagename, productprice, stock, cuisineid, description) values(10,'Lasagna', 'lasagna.jpg', 150, 200, 'FBC3', 'Lasagne are a type of pasta, possibly one of the oldest types, made of very wide, flat sheets.');
insert into products(productid, productname, imagename, productprice, stock, cuisineid, description) values(11,'Stuffed Ravioli', 'stuffedravioli.jpg', 180, 200, 'FBC3', 'Ravioli Stuffed with Mozzarella, Basil and Walnut Pesto is a delicious Italian delight, which can make for a perfect brunch delicacy.');
insert into products(productid, productname, imagename, productprice, stock, cuisineid, description) values(12,'Taco', 'taco.jpg', 200, 200, 'FBC4', 'A taco is a traditional Mexican dish consisting of a small hand-sized corn or wheat tortilla topped with a filling.');
insert into products(productid, productname, imagename, productprice, stock, cuisineid, description) values(13,'Burrito', 'burrito.jpg', 100, 200, 'FBC4', 'A burrito is a dish in Mexican and Tex-Mex cuisine, consisting of a flour tortilla wrapped into a sealed cylindrical shape around various ingredients.');