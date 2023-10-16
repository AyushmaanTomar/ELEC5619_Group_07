import { useState } from 'react';
import { productAPI } from './itemAPI';
import { useMutation, useQuery, useQueryClient } from 'react-query';
import { Item } from './listings';

export function useProducts() {
  const [page, setPage] = useState(0);
  let queryInfo = useQuery(['product', page], () => productAPI.get(page + 1), {
    keepPreviousData: true,
    // staleTime: 5000,
  });
  // console.log(queryInfo);
  return { ...queryInfo, page, setPage };
}

export function useSaveProduct() {
  const queryClient = useQueryClient();
  return useMutation((product: Item) => productAPI.put(product), {
    onSuccess: () => queryClient.invalidateQueries('products'),
  });
}