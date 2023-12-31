import { useState } from 'react';
import { useMutation, useQuery, useQueryClient } from 'react-query';
import { userAPI } from './profileAPI';
import { useError } from 'src/errorContext';

export function useProfile(): any {
  const [page, setPage] = useState(0);
  const { showError } = useError();

  try {
  let data = userAPI();
  } catch (error: any) {
    showError(error);
  }

  let queryInfo = useQuery(['profile', page], () => userAPI(), {
    keepPreviousData: true,
  });

  if (queryInfo.error) {
    console.error("Error fetching profile:", queryInfo.error);
  }

  return { ...queryInfo, page, setPage };
}

