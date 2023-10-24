import React, { useState } from 'react'
import itemList from 'src/components/items/itemList';
import item from './db.json'


const SearchBarFilter = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredData, setFilteredData] = useState(item.products);


  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setSearchTerm(value);
    filterData(value);
  };


  const filterData = (searchTerm: string) => {
    const filteredData = item.products.filter((item) =>
      item.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
    setFilteredData(filteredData);
  };


  return (
    <div>
      <input
        type="text"
        placeholder="Search..."
        value={searchTerm}
        onChange={handleInputChange}
      />
    </div>
  );
};

export default SearchBarFilter;

;