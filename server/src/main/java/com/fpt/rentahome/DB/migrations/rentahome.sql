# change rent_type to Enum in property table
alter table property
    modify column rent_type ENUM ('Daily', 'Monthly');

# change status to Enum in property table
alter table property
    modify column status ENUM ('Available', 'Rented');

# change status to Enum in reservation table
alter table reservation
    modify column status ENUM ('Pending', 'Approved', 'Rejected');


# change category to Enum in property table
alter table property
    modify column category ENUM ('House', 'Apartment', 'Condo', 'Townhouse', 'Villa', 'Studio', 'Other');