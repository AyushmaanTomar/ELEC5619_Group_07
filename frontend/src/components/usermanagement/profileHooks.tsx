import { useState } from 'react';
import { useMutation, useQuery, useQueryClient } from 'react-query';
import { userAPI } from './profileAPI';

export function useProfile() {
  const [page, setPage] = useState(0);
  let queryInfo = useQuery(['profile', page], () => userAPI.get(page + 1), {
    keepPreviousData: true,
    // staleTime: 5000,
  });
  // console.log(queryInfo);
  return { ...queryInfo, page, setPage };
}

