import { useState } from 'react';
import { useMutation, useQuery, useQueryClient } from 'react-query';
import { userAPI } from './profileAPI';

export function useProfile() {
  const [page, setPage] = useState(0);
  let queryInfo = useQuery(['profile', page], () => userAPI.get(page + 1), {
    keepPreviousData: true,
  });

  if (queryInfo.error) {
    console.error("Error fetching profile:", queryInfo.error);
  }

  return { ...queryInfo, page, setPage };
}

