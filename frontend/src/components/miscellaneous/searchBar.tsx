import React, { useState, useEffect } from 'react';

interface Props {
  value: string;
  onChange: (value: string) => void;
}

const SearchBarFilter: React.FC<Props> = ({ value, onChange }) => {
  return (
    <input 
      type="text" 
      placeholder="Search products..." 
      value={value} 
      onChange={e => onChange(e.target.value)}
    />
  );
}

export default SearchBarFilter;
